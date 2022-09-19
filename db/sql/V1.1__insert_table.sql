--本のリスト初期データ
--idカラムはオートインクリメントなので不要
INSERT INTO booktable
(book_name, volume_num,author_name,published_date)
VALUES
( 'HUNTER X HUNTER',36,'冨樫義博','2018-10-04'),
( 'ベルセルク',40,'三浦健太郎','2018-09-28'),
( 'ドリフターズ',6,'平野耕太','2018-11-30'),
( '羅生門',1,'芥川龍之介','1915-11-01')
;