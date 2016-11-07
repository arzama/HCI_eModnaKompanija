using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ModnaKompanija_API.Models
{
    public class Ocjene
    {

        public int Id { get; set; }
        public double Ocjena { get; set; }
        public Proizvodi Proizvod { get; set; }
        public int ProizvodId { get; set; }
        public Korisnici Korisnik { get; set; }
        public int KorisnikId { get; set; }
    }
}