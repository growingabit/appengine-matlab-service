package it.growbit.matlab.controller;

import it.growbit.matlab.model.Last24HoursAvg;
import it.growbit.matlab.model.Next24HourAvg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/matlab", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String matlab() {
        String str = "";

        str += "isMCRInitialized: " + this.matlab.isMCRInitialized();

        str += this.matlab.getEnvs();

        return str;
    }

    @RequestMapping(value = "/matlab/criptoOracleValori", method = RequestMethod.POST)
    public Next24HourAvg matlab_criptoOracleValori(@RequestBody Last24HoursAvg last24houravgs) throws Exception {

        Double forecast = this.matlab.generic_invocation("criptoOracleValori", "criptoOracleValori", last24houravgs);

        Next24HourAvg next24houravg = new Next24HourAvg(forecast);

        return next24houravg;
    }

    @RequestMapping(value = "/matlab/superCriptoOracleTrend", method = RequestMethod.POST)
    public Next24HourAvg matlab_superCriptoOracleTrend(@RequestBody Last24HoursAvg last24houravgs) throws Exception {

        Double forecast = this.matlab.generic_invocation("superCriptoOracleTrend", "superCriptoOracleTrend", last24houravgs);

        Next24HourAvg next24houravg = new Next24HourAvg(forecast);

        return next24houravg;
    }

    @RequestMapping(value = "/matlab/{packageName}/{methodName}", method = RequestMethod.POST)
    public Next24HourAvg matlab_generics(
            @RequestBody Last24HoursAvg matlab_payload,
            @PathVariable(value="packageName") String packageName,
            @PathVariable(value="methodName") String methodName
    ) throws Exception {

        Double forecast = this.matlab.generic_invocation(packageName, methodName, matlab_payload);

        Next24HourAvg next24houravg = new Next24HourAvg(forecast);

        return next24houravg;
    }
}
