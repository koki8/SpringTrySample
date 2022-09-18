-- DB切り替え
\c library

-- テーブル作成
CREATE TABLE IF NOT EXISTS libraryschema.booktable(
id SERIAL NOT NULL,
book_name VARCHAR(50) NOT NULL,
volume_num INT NOT NULL,
author_name VARCHAR(50) NOT NULL,
published_date DATE NOT NULL,
PRIMARY KEY(id)
);


-- 権限追加
GRANT ALL PRIVILEGES ON libraryschema.booktable TO libraryuser;

--本のリスト初期データ
--idカラムはオートインクリメントなので不要
INSERT INTO libraryschema.booktable
(book_name, volume_num,author_name,published_date)
VALUES
( 'HUNTER X HUNTER',36,'冨樫義博','2018-10-04'),
( 'ベルセルク',40,'三浦健太郎','2018-09-28'),
( 'ドリフターズ',6,'平野耕太','2018-11-30'),
( '羅生門',1,'芥川龍之介','1915-11-01')
;