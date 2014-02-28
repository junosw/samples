import play.api.libs.json._
import play.api.libs.functional.syntax._

object Descriptor {

  implicit val reader = (
      (__ \ 'id).readNullable[String] and
      (__ \ 'href).readNullable[String] and
      (__ \ 'doc).readNullable[Doc] and
      (__ \ 'ext).readNullable[Ext] and
      (__ \ 'name).readNullable[String] and
      (__ \ '_type).readNullable[String] and
      (__ \ 'descriptors).readNullable[Seq[Descriptor]]
    )(Descriptor.apply _)

  implicit val writer = Json.writes[Descriptor]

  def fromJson(js: JsValue): Either[JsError, Descriptor] = {
    js.validate[Descriptor] match {
      case JsSuccess(x, _) => Right(x)
      case errors: JsError => Left(errors)
    }
  }

}

/**
 *
 * @param id
 * @param href
 * @param doc
 * @param ext
 * @param name The use of the 'name' property usually indicates an ambiguity in the application semantics. Thus, it SHOULD only be used when creating an ALPS profile that describes an existing design.
 * @param _type
 * @param descriptors
 */
case class Descriptor (id: Option[String],
                       href: Option[String],
                       doc: Option[Doc],
                       ext: Option[Ext],
                       name: Option[String],
                       _type: Option[String],
                       descriptors: Option[Seq[Descriptor]])

