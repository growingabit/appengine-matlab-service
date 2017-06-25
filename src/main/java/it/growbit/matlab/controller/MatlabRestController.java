package it.growbit.matlab.controller;

import com.mathworks.toolbox.javabuilder.MWException;
import it.growbit.matlab.model.Last24HoursAvg;
import it.growbit.matlab.model.Next24HourAvg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by name on 24/06/17.
 */
@RestController
public class MatlabRestController {

    @Autowired
    MatlabController matlab;

    @RequestMapping(value = "/_ah/health", method = RequestMethod.GET)
    public String healthcheck() {
        return "ok";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "see /matlab for status";
    }

    @RequestMapping(value = "/matlab", method = RequestMethod.GET)
    public String matlab() {
        String str = "";

        str += "isMCRInitialized: " + this.matlab.isMCRInitialized();

        str += this.matlab.getEnvs();

        return str;
    }

    @RequestMapping(value = "/matlab/criptoOracleValori", method = RequestMethod.POST)
    public Next24HourAvg matlab_criptoOracleValori(@RequestBody Last24HoursAvg last24houravgs) throws MWException {

        Double forecast = this.matlab.criptoOracleValori(last24houravgs);

        Next24HourAvg next24houravg = new Next24HourAvg(forecast);

        return next24houravg;
    }

    @RequestMapping(value = "/matlab/superCriptoOracleTrend", method = RequestMethod.POST)
    public Next24HourAvg matlab_superCriptoOracleTrend(@RequestBody Last24HoursAvg last24houravgs) throws MWException {

        Double forecast = this.matlab.superCriptoOracleTrend(last24houravgs);

        Next24HourAvg next24houravg = new Next24HourAvg(forecast);

        return next24houravg;
    }
}
