/*
 * This file is part of Nextbeat services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package controllers.home

import model.component.util.ViewValuePageLayout
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, MessagesControllerComponents}
import scala.concurrent._

// 施設
//~~~~~~~~~~~~~~~~~~~~~
class HomeController @javax.inject.Inject()(
  cc: MessagesControllerComponents
) extends AbstractController(cc) with I18nSupport {
  implicit lazy val executionContext = defaultExecutionContext

  def view = Action.async { implicit request =>
  Future{
      Ok(views.html.site.home.Main(ViewValuePageLayout(id = request.uri)))
    }
  }

  /**
   * 検索
   */
    /*
  def search = Action.async { implicit request =>
    formForFacilitySearch.bindFromRequest.fold(
      errors => {
        for {
          locSeq      <- daoLocation.filterByIds(Location.Region.IS_PREF_ALL)
          facilitySeq <- facilityDao.findAll
        } yield {
          val vv = SiteViewValueFacilityList(
            layout     = ViewValuePageLayout(id = request.uri),
            location   = locSeq,
            facilities = facilitySeq
          )
          BadRequest(views.html.site.facility.list.Main(vv, errors))
        }
      },
      form   => {
        for {
          locSeq      <- daoLocation.filterByIds(Location.Region.IS_PREF_ALL)
          facilitySeq <- form.locationIdOpt match {
            case Some(id) =>
              for {
                locations   <- daoLocation.filterByPrefId(id)
                facilitySeq <- facilityDao.filterByLocationIds(locations.map(_.id))
              } yield facilitySeq
            case None     => facilityDao.findAll
          }
        } yield {
          val vv = SiteViewValueFacilityList(
            layout     = ViewValuePageLayout(id = request.uri),
            location   = locSeq,
            facilities = facilitySeq
          )
          Ok(views.html.site.facility.list.Main(vv, formForFacilitySearch.fill(form)))
        }
      }
    )
  }
  */
}