CREATE OR REPLACE FUNCTION update_tags() RETURNS void AS $$
DECLARE
    -- declarations
    cursor_rss                       refcursor;
    rec_nowdata                         record;
BEGIN
	TRUNCATE TABLE tags_analyze;
	OPEN cursor_rss FOR
						SELECT * FROM rss;

	LOOP
		FETCH cursor_rss INTO rec_nowdata;
		IF NOT FOUND THEN
			EXIT;
		END IF;
			INSERT INTO tags_analyze(word,maintype,subtype1,subtype2,url)
			SELECT word,type,subtype1,subtype2,rec_nowdata.link FROM ja_analyze(rec_nowdata.title)
			WHERE length(word) >1
				AND type = '名詞'
				AND subtype1 in('固有名詞','一般');
				--select word,count(*) from tags_analyze group by word order by count(*) desc;
	END LOOP;
	CLOSE cursor_rss;
	DELETE FROM tags_analyze WHERE word like 'ww%' OR word like 'ｗｗ%' OR word like 'ーー%';
END;
$$ LANGUAGE plpgsql;