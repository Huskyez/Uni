using System;

namespace model
{
    [Serializable]
    public class Angajat : HasId<String>
    {
        public String Id { get; set; }
        public String Password { get; set; }

        public Angajat(String Id, String Password)
        {
            this.Id = Id;
            this.Password = Password;
        }

        public override String ToString()
        {
            return "use=" + Id + "|pass=" + Password;
        }
    }
}
