CREATE OR REPLACE FUNCTION update_tags_association() RETURNS void AS $$
DECLARE
    -- declarations
    cursor_word                       refcursor;
    rec_nowdata                         record;
    total_x_now                        integer;
BEGIN
	TRUNCATE TABLE tags_association;
	TRUNCATE TABLE tags_stat;
	
	INSERT INTO tags_stat (word, total)
	SELECT word,count(*) FROM tags_analyze GROUP BY word;
		
	OPEN cursor_word FOR
						SELECT DISTINCT word FROM tags_analyze;

	LOOP
		FETCH cursor_word INTO rec_nowdata;
		IF NOT FOUND THEN
			EXIT;
		END IF;
			SELECT total INTO total_x_now FROM tags_stat WHERE word = rec_nowdata.word;

			INSERT INTO tags_association(word_x,word_y,total_xy,total_x,total_y)
			SELECT rec_nowdata.word,ta.word,count(*),total_x_now,ts.total
			FROM (SELECT DISTINCT word,url FROM tags_analyze) ta INNER JOIN tags_stat ts ON ta.word = ts.word
			WHERE url IN (SELECT DISTINCT url 
							FROM tags_analyze 
							WHERE word=rec_nowdata.word) 
			AND ta.word<>rec_nowdata.word 
			GROUP BY ta.word,ts.total 
			ORDER BY count(*) DESC;
	END LOOP;
	CLOSE cursor_word;	
	
	UPDATE tags_association SET total =(SELECT count(*) FROM (SELECT url FROM tags_analyze GROUP BY url) tau);
	UPDATE tags_association SET support = cast(total_xy as DOUBLE PRECISION) / cast(total as DOUBLE PRECISION);
	UPDATE tags_association SET confidence = cast(total_xy as DOUBLE PRECISION) / cast(total_x as DOUBLE PRECISION);
	UPDATE tags_association SET lift = confidence / (cast(total_y as DOUBLE PRECISION) / cast(total as DOUBLE PRECISION));
	UPDATE tags_association SET asoc = confidence * support * log(lift);
END;
$$ LANGUAGE plpgsql;