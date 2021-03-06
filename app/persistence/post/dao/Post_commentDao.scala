package persistence.post.dao

import java.time.LocalDateTime
import scala.concurrent.Future

import slick.jdbc.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider

import persistence.post.model.Post
import persistence.post.model.PostComment
import persistence.udb.model.User

// DAO: ユーザ情報
//~~~~~~~~~~~~~~~~~~
class PostCommentDAO @javax.inject.Inject()(
                                      val dbConfigProvider: DatabaseConfigProvider
                                    ) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  // --[ リソース定義 ] --------------------------------------------------------
  lazy val slick = TableQuery[PostCommentTable]

  // --[ データ処理定義 ] ------------------------------------------------------
  /**
    * Postに対するコメント情報を追加する
    */
  def add(data: PostComment) =
    db.run {
      data.id match {
        case None    => slick returning slick.map(_.id) += data
        case Some(_) => DBIO.failed(
          new IllegalArgumentException("The given object is already assigned id.")
        )
      }
    }

  /**
    * postに対するコメントを取得する
    */

  def get(id: PostComment.Id) =
    db.run {
      slick
        .filter(_.id === id)
        .result.headOption
    }

  def getFilterByPostId(id: Post.Id) =
    db.run {
      slick
        .filter(_.postId === id)
        .result
    }

  def getFilterByUserId(id: User.Id) =
    db.run {
      slick
        .filter(_.userId === id)
        .result
    }

  /**
    * 削除する
    */
  def delete(id: Post.Id) =
    db.run {
      slick
        .filter(_.id === id)
        .delete
    }

  // --[ テーブル定義 ] --------------------------------------------------------
  class PostCommentTable(tag: Tag) extends Table[PostComment](tag, "post_comment") {

    // Table's columns
    /* @1 */ def id        = column[PostComment.Id]       ("id", O.PrimaryKey, O.AutoInc)  // POST ID
    /* @2 */ def content   = column[String]                ("content")           // postに対するコメント内容 content
    /* @3 */ def postId    = column[Post.Id]               ("post_id")           // 対応するpostのid
    /* @4 */ def userId    = column[User.Id]               ("user_id")           // 投稿したユーザーのid
    /* @5 */ def updatedAt = column[LocalDateTime]         ("updated_at")        // データ更新日
    /* @6 */ def createdAt = column[LocalDateTime]         ("created_at")        // データ作成日

    // The * projection of the table
    def * = (
      id.?, content, postId, userId.?, updatedAt, createdAt
    ) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (PostComment.apply _).tupled,
      /** The bidirectional mappings : Model => Tuple(table) */
      (v: TableElementType) => PostComment.unapply(v).map(_.copy(
        _5 = LocalDateTime.now
      ))
    )
  }
}