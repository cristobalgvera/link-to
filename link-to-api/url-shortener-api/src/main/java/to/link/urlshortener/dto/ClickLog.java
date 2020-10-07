package to.link.urlshortener.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClickLog {
    private int totalLogs;
    private List<LocalDateTime> clickLogs = new ArrayList<>();

    public ClickLog() {
    }

    public int getTotalLogs() {
        return totalLogs;
    }

    public List<LocalDateTime> getClickLogs() {
        return clickLogs;
    }

    public void setClickLogs(List<LocalDateTime> clickLogs) {
        this.clickLogs = clickLogs;
        this.totalLogs = clickLogs.size();
    }

    @Override
    public String toString() {
        return "ClickLog{" +
                "totalLogs=" + totalLogs +
                ", clickLogs=" + clickLogs +
                '}';
    }

}
