package com.akash.saison.service;

import com.akash.saison.dto.Permit;
import com.akash.saison.dto.PermitData;

import java.util.Date;
import java.util.List;

public interface IPermitService {
    List<Permit> getByApplicantName(String applicant);
    List<Permit> getExpiredPermits(Date expirationDate);

    List<Permit> getByAddress(String address);
}
