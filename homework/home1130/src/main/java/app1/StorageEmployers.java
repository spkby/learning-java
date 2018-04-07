package app1;

public interface StorageEmployers<Employer> {

    void add(Employer employer);
    void delete(Employer employer);
    void edit(Employer oldEmployer,Employer newEmployer);
    Employer find(String lastName);

}
