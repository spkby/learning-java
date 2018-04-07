package app1;

import java.util.ArrayList;
import java.util.List;

public class Employers implements StorageEmployers<Employer>{

    private List<Employer> employers;

    public Employers(){
        employers = new ArrayList<>();
    }

    @Override
    public void add(Employer employer) {
        employers.add(employer);
    }

    @Override
    public void delete(Employer employer) {
        if(employers.indexOf(employer) == -1){
            throw new IllegalArgumentException("Нет такого Работника");
        }
        employers.remove(employers.indexOf(employer));
    }

    @Override
    public void edit(Employer oldEmployer, Employer newEmployer) {
        if(employers.indexOf(oldEmployer) == -1){
            throw new IllegalArgumentException("Нет такого Работника");
        }
        employers.set(employers.indexOf(oldEmployer),newEmployer);
    }

    @Override
    public Employer find(String lastName) {
        for (Employer employer : employers) {
            if(employer.getLastName().equals(lastName)){
                return employer;
            }
        }
        return null;
    }
}
