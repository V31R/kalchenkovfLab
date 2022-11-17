package kalchenko.bank.utils;

import kalchenko.bank.entity.Bank;

import java.util.Comparator;

public class BankComparator implements Comparator<Bank> {
    @Override
    public int compare(Bank left, Bank right) {
        var interestRateCompare = left.getInterestRate().compareTo(right.getInterestRate());
        if(interestRateCompare != 0){
            return -interestRateCompare;//чем меньше, тем лучше
        }

        if(left.getBankRate() != right.getBankRate()){
            return left.getBankRate() > right.getBankRate() ? 1: -1;
        }

        if(left.getUserNumber() != right.getUserNumber()){
            return left.getUserNumber() > right.getUserNumber() ? 1: -1;
        }

        if(left.getOfficesNumber() != right.getOfficesNumber()){
            return left.getOfficesNumber() > right.getOfficesNumber() ? 1: -1;
        }

        if(left.getAtmNumber() != right.getAtmNumber()){
            return left.getAtmNumber() > right.getAtmNumber() ? 1: -1;
        }

        if(left.getEmployeeNumber() != right.getEmployeeNumber()){
            return left.getEmployeeNumber() > right.getEmployeeNumber() ? 1: -1;
        }

        return 0;
    }
}
