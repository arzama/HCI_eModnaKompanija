using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ModnaKompanija_API.Models
{
    public class Proizvodi
    {
        public int Id { get; set; }

        public string NazivProizvoda { get; set; }
        public VrstaProizvoda VrstaProizvoda { get; set; }
        public string Materijal { get; set; }
        public int VrstaProizvodaId { get; set; }
        public string Boja { get; set; }
     
        public string Datum { get; set; }
        public string OpisProizvoda { get; set; }
        public string Kolekcija { get; set; }
     
    }
}