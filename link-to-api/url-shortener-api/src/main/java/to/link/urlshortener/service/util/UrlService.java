package to.link.urlshortener.service.util;

import to.link.urlshortener.dto.ClickLog;
import to.link.urlshortener.model.Url;

public interface UrlService extends CrudMethods<Url, String> {
    String redirect(String shortUri);

    ClickLog getClickLogs(String shortUri);
}
