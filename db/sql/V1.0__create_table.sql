-- テーブル作成
CREATE TABLE IF NOT EXISTS booktable(
id SERIAL NOT NULL,
book_name VARCHAR(50) NOT NULL,
volume_num INT NOT NULL,
author_name VARCHAR(50) NOT NULL,
published_date DATE NOT NULL,
PRIMARY KEY(id)
);

