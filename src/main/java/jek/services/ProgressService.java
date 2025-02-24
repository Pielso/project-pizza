package jek.services;

import jek.controllers.LoginController;
import jek.models.Progress;
import jek.repositories.ProgressRepository;
import jek.services.system.DatabaseService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgressService {
    ProgressRepository progressRepository;

    public ProgressService(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public void saveNewProgress(int userId){
        progressRepository.SaveNewProgress(userId, new BigDecimal(10000), new BigDecimal(50000), 10, 7, 1, 0);
    }

    public void UpdateActiveProgress(Progress progress){
        progressRepository.UpdateProgressById(progress.getUserId(), progress.getCash(), progress.getLoan(), progress.getInterestRate(), progress.getCustomersPerDay(), progress.getRestaurantSize(), progress.getDaysPlayed());
    }

    public Progress getProgressById(int userId){
        return progressRepository.getProgressById(userId);
    }

    public Progress getProgress(){
        return LoginController.activeProgress;
    }
}
