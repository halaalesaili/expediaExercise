package beans;

/**
 *
 * @author Hala Al Esaili
 */
public class UserInfo {

    private Persona persona;
    private String userId;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    static class Persona {

        private String personaType;

        public String getPersonaType() {
            return personaType;
        }

        public void setPersonaType(String personaType) {
            this.personaType = personaType;
        }
    }
}
