﻿using model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace services
{
    public interface IFlightObserver
    {
        void TicketBought(IEnumerable<Zbor> zboruri);
    }
}