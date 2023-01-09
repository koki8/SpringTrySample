--本のリスト初期データ
--idカラムはオートインクリメントなので不要
--　ユーザー名：hoge3　パスワード：123456
INSERT INTO loginuser
(user_name, password, authority)
VALUES
( 'hoge3', '$2a$10$erGD.E7tPYz5Pj6hYg95DuF996pycQvgrraWJYABuwbEuCO.HisyW', 'ROLE_USER')
;