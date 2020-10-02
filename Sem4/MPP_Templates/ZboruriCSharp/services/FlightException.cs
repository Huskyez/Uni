using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace services
{
    public class FlightException : Exception
    {
        public FlightException() : base() { }

        public FlightException(String msg) : base(msg) { }

        public FlightException(String msg, Exception e) : base(msg, e) { } 
    }
}
