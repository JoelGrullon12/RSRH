package common;

import java.time.LocalDate;
import java.time.Period;

public class DateUtil {

    public static String calcularPeriodo(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            return "Fechas inválidas";
        }

        if (fechaFin.isBefore(fechaInicio)) {
            return "La fecha final debe ser posterior a la inicial";
        }

        Period periodo = Period.between(fechaInicio, fechaFin);
        int años = periodo.getYears();
        int meses = periodo.getMonths();

        if (años == 0 && meses == 0) {
            return "Menos de un mes";
        }

        StringBuilder resultado = new StringBuilder();

        if (años > 0) {
            resultado.append(años).append(años == 1 ? " año" : " años");
        }

        if (meses > 0) {
            if (resultado.length() > 0) {
                resultado.append(" y ");
            }
            resultado.append(meses).append(meses == 1 ? " mes" : " meses");
        }

        return resultado.toString();
    }
}
