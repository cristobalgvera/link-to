package cl.crisgvera.urlshortener.service.util;

import cl.crisgvera.urlshortener.dto.ClickLog;
import cl.crisgvera.urlshortener.model.Url;

public interface UrlService extends CrudMethods<Url, String> {
    String redirect(String shortUri);

    ClickLog getClickLogs(String shortUri);
}
