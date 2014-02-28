
import play.api.libs.json._
import play.api.libs.functional.syntax._

object Collection {

  implicit val reader = (
    (__ \ 'version).read[String] and
    (__ \ 'href).read[String] and
    (__ \ 'links).readNullable[Seq[Link]] and
    (__ \ 'items).readNullable[Seq[Item]] and
    (__ \ 'queries).readNullable[Seq[Query]] and
    (__ \ 'template).readNullable[Template] and
    (__ \ 'error).readNullable[Error]
  )(Collection.apply _)

  implicit val writer = Json.writes[Collection]

  def fromJson(js: JsValue): Either[JsError, Collection] = {
    js.validate[Collection] match {
      case JsSuccess(x, _) => Right(x)
      case errors: JsError => Left(errors)
    }
  }

}

case class Collection(version: String,
                      href: String,
                      links: Option[Seq[Link]],
                      items: Option[Seq[Item]],
                      queries: Option[Seq[Query]],
                      template: Option[Template],
                      error: Option[Error])   { def toJson: JsValue = Json.toJson(this) }
