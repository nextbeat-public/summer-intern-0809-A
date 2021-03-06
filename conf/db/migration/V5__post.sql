-- spotにくっつく詳細説明
--------------
CREATE TABLE "post" (
  "id"          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  "title"       VARCHAR(255) NOT NULL,
  "content"     TEXT         NOT NULL,
  "image"       LONGTEXT,  --今回はbase64でエンコードして保存　ストレージを使うことが望ましい
  "user_id"     INT          NOT NULL,
  "spot_id"     INT          NOT NULL,
  "updated_at"  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  "created_at"  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

INSERT INTO "post" ("title", "content", "image", "user_id", "spot_id") VALUES ('天気の子', '気象神社',  null, 1, 1);
INSERT INTO "post" ("title", "content", "image", "user_id", "spot_id") VALUES ('天気の子', '代々木会館',  null, 1, 2);
INSERT INTO "post" ("title", "content", "image", "user_id", "spot_id") VALUES ('天気の子', '朝日稲荷神社',  null, 1, 3);
INSERT INTO "post" ("title", "content", "image", "user_id", "spot_id") VALUES ('北の国から', '五郎の石の家',  null, 1, 4);
INSERT INTO "post" ("title", "content", "image", "user_id", "spot_id") VALUES ('北の国から', '布部駅',  null, 1, 5);
INSERT INTO "post" ("title", "content", "image", "user_id", "spot_id") VALUES ('北の国から', '鳥沼公園',  null, 1, 6);
INSERT INTO "post" ("title", "content", "image", "user_id", "spot_id") VALUES ('北の国から', '富良野ドラマ館',  null, 1, 7);
INSERT INTO "post" ("title", "content", "image", "user_id", "spot_id") VALUES ('天気の子', 'アタミビル',  null, 1, 8);