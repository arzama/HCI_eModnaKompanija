﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ModnaKompanija_API.Models
{
    public class Korisnici
    {
        public int Id { get; set; }
       
        public string Ime { get; set; }
        public string Prezime { get; set; }
        public string Email { get; set; }
        public string Adresa { get; set; }
        public string Kontakt { get; set; }
        public string KorisnickoIme { get; set; }
        public string Lozinka { get; set; }
        
    }
    
}