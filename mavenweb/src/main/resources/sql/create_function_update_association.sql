CREATE OR REPLACE FUNCTION update_tags_association() RETURNS void AS $$
DECLARE
    -- declarations
    cursor_word                       refcursor;
    rec_nowdata                         record;
    total_x_now                        integer;
BEGIN
	CREATE TEMP TABLE tags_analyze_temp(
		id SERIAL,
		word VARCHAR(200) NOT NULL,
		maintype VARCHAR(50) NOT NULL,
		subtype1 VARCHAR(50),
		subtype2 VARCHAR(50),
		url VARCHAR(500) NOT NULL,
		PRIMARY KEY(id)
	) ON COMMIT DROP;

	CREATE TEMP TABLE tags_stat_temp(
		word VARCHAR(200) NOT NULL,
		total INTEGER NOT NULL,
		PRIMARY KEY(word)
	) ON COMMIT DROP;
	
	CREATE TEMP TABLE tags_association_temp(
		word_x VARCHAR(200) NOT NULL,
		word_y VARCHAR(200) NOT NULL,
		total_xy INTEGER,
		total INTEGER,
		total_x INTEGER,
		total_y INTEGER,
		confidence DOUBLE PRECISION,
		support DOUBLE PRECISION,
		lift DOUBLE PRECISION,
		asoc DOUBLE PRECISION,
		PRIMARY KEY(word_x,word_y)
	) ON COMMIT DROP;
	
	INSERT INTO tags_analyze_temp(word,maintype,subtype1,subtype2,url)
	SELECT word,maintype,subtype1,subtype2,url FROM tags_analyze;
	
	INSERT INTO tags_stat_temp (word, total)
	SELECT word,count(*) FROM tags_analyze_temp GROUP BY word;
		
	OPEN cursor_word FOR
						SELECT DISTINCT word FROM tags_analyze_temp;

	LOOP
		FETCH cursor_word INTO rec_nowdata;
		IF NOT FOUND THEN
			EXIT;
		END IF;
			SELECT total INTO total_x_now FROM tags_stat_temp WHERE word = rec_nowdata.word;

			INSERT INTO tags_association_temp(word_x,word_y,total_xy,total_x,total_y)
			SELECT rec_nowdata.word,ta.word,count(*),total_x_now,ts.total
			FROM (SELECT DISTINCT word,url FROM tags_analyze_temp) ta INNER JOIN tags_stat_temp ts ON ta.word = ts.word
			WHERE url IN (SELECT DISTINCT url 
							FROM tags_analyze_temp 
							WHERE word=rec_nowdata.word) 
			AND ta.word<>rec_nowdata.word 
			GROUP BY ta.word,ts.total 
			ORDER BY count(*) DESC;
	END LOOP;
	CLOSE cursor_word;	
	
	UPDATE tags_association_temp SET total =(SELECT count(*) FROM (SELECT url FROM tags_analyze GROUP BY url) tau);
	UPDATE tags_association_temp SET support = cast(total_xy as DOUBLE PRECISION) / cast(total as DOUBLE PRECISION);
	UPDATE tags_association_temp SET confidence = cast(total_xy as DOUBLE PRECISION) / cast(total_x as DOUBLE PRECISION);
	UPDATE tags_association_temp SET lift = confidence / (cast(total_y as DOUBLE PRECISION) / cast(total as DOUBLE PRECISION));
	UPDATE tags_association_temp SET asoc = confidence * support * log(lift);
	
	TRUNCATE TABLE tags_association;
	
	INSERT INTO tags_association (word_x,word_y,total_xy,total,total_x,total_y,confidence,support,lift,asoc)
	SELECT word_x,word_y,total_xy,total,total_x,total_y,confidence,support,lift,asoc FROM tags_association_temp;

	TRUNCATE TABLE tags_stat;

	INSERT INTO tags_stat (word, total)
	SELECT word, total FROM tags_stat_temp;
	
END;
$$ LANGUAGE plpgsql;