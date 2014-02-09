--CREATE DATABASE newsp WITH ENCODING = 'UTF-8' TEMPLATE = template0;

DROP TABLE IF EXISTS rss;
CREATE TABLE rss(
    id SERIAL,
    blog_title VARCHAR(200) NOT NULL,
    category1 VARCHAR(100) NOT NULL,
    category2 VARCHAR(100) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    link VARCHAR(255) NOT NULL,
    content TEXT,
    img_url VARCHAR(255),
    date_written TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS tags_analyze;
CREATE TABLE tags_analyze(
	id SERIAL,
	word VARCHAR(200) NOT NULL,
	maintype VARCHAR(50) NOT NULL,
	subtype1 VARCHAR(50),
	subtype2 VARCHAR(50),
	url VARCHAR(500) NOT NULL,
	PRIMARY KEY(id)
);

DROP TABLE IF EXISTS tags_stat;
CREATE TABLE tags_stat(
	word VARCHAR(200) NOT NULL,
	total INTEGER NOT NULL,
	PRIMARY KEY(word)
);

DROP TABLE IF EXISTS tags_association;
CREATE TABLE tags_association(
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
);