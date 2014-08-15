package Abstract_Factory;

public class Main {
    public static void main(String[] args) {

        FormManager fm = new RegManager();
        fm.createForm().generate();
        fm.createValidator().validate();
    }
}


interface Form {
    void generate();
}

class RegForm implements Form {

    @Override
    public void generate() {
        System.out.println(" hello RegForm ");
    }
}

class AuthForm implements Form {

    @Override
    public void generate() {
        System.out.println(" hello AuthForm ");
    }
}

class PostForm implements Form {

    @Override
    public void generate() {
        System.out.println(" hello PostForm ");
    }
}

interface Validator {
    void validate();
}

class RegValidator implements Validator {

    @Override
    public void validate() {
        System.out.println(" Validate RegForm ");
    }

}

class AuthValidator implements Validator {
    @Override
    public void validate() {
        System.out.println(" Validate AuthForm ");
    }
}

class PostValidator implements Validator {
    @Override
    public void validate() {
        System.out.println(" Validate PostForm ");
    }
}


interface FormManager {
    Form createForm();

    Validator createValidator();
}

class RegManager implements FormManager {
    @Override
    public Form createForm() {
        return new RegForm();
    }

    @Override
    public Validator createValidator() {
        return new RegValidator();
    }
}

class AuthManager implements FormManager {
    @Override
    public Form createForm() {
        return new AuthForm();
    }

    @Override
    public Validator createValidator() {
        return new AuthValidator();
    }
}

class PostManager implements FormManager {
    @Override
    public Form createForm() {
        return new PostForm();
    }

    @Override
    public Validator createValidator() {
        return new PostValidator();
    }
}
