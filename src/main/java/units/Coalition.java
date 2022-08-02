package units;

import java.util.ArrayList;
import java.util.List;

public class Coalition {
    String coalitionName;
    List<Army> armies;

    public Coalition() {
        this.armies = new ArrayList<>();
    }

    public List<Army> getSubunits() {
        return armies;
    }
    public String getCoalitionName() {
        return coalitionName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Coalition{");
        sb.append("coalitionName='").append(coalitionName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
