using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ModnaKompanija_API.Models
{
    public class NarudzbaStavke
    {
        public int Id { get; set; }
        public Materijali Materijali{ get; set; }
        public int MaterijaliId { get; set; }
        public Narudzba Narudzba { get; set; }
        public int NarudzbaId { get; set; }
        public int Kolicina { get; set; }
    
    }
}