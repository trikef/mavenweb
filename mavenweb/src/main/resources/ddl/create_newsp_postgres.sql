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
    date_written TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id)
);