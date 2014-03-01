CREATE OR REPLACE FUNCTION update_tags_assoc_temp() RETURNS void AS $$

BEGIN
	TRUNCATE TABLE tags_analyze_temp;

	INSERT INTO tags_analyze_temp(word,maintype,subtype1,subtype2,url)
	SELECT word,maintype,subtype1,subtype2,url FROM tags_analyze;

	TRUNCATE TABLE tags_stat_temp;

	INSERT INTO tags_stat_temp (word, total)
	SELECT word,count(*) FROM tags_analyze_temp GROUP BY word;
	
	TRUNCATE TABLE tags_association_temp;
	INSERT INTO tags_association_temp(word_x,word_y,total_xy,total_x,total_y,total,support,confidence,lift,asoc)
	SELECT word_x,word_y,total_xy,total_x,total_y,total,support,confidence,lift,asoc FROM tags_association;
END;
$$ LANGUAGE plpgsql;