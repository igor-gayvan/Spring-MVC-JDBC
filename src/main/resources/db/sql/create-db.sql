--DROP TABLE articles IF EXISTS;

CREATE TABLE articles (
  article_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1) PRIMARY KEY,
  article_url VARCHAR(4000),
  article_name  VARCHAR(1000)
);