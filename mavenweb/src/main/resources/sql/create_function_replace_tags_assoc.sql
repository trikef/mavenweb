CREATE OR REPLACE FUNCTION replace_tags_assoc() RETURNS void AS $$
BEGIN
	TRUNCATE TABLE tags_association;

	INSERT INTO tags_association (word_x,word_y,total_xy,total,total_x,total_y,confidence,support,lift,asoc)
	SELECT word_x,word_y,total_xy,total,total_x,total_y,confidence,support,lift,asoc FROM tags_association_temp;
	
	TRUNCATE TABLE tags_stat;
	
	INSERT INTO tags_stat (word, total)
	SELECT word, total FROM tags_stat_temp;
END;
$$ LANGUAGE plpgsql;