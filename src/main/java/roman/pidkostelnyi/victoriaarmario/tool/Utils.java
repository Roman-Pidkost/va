package roman.pidkostelnyi.victoriaarmario.tool;

import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static roman.pidkostelnyi.victoriaarmario.tool.Constants.KIEV_ZONE;


public class Utils {

    public static LocalDate dateToLocalDate(@Nullable Date date) {
        return date == null ? null : date.toInstant().atZone(ZoneId.of(KIEV_ZONE)).toLocalDate();
    }
}
