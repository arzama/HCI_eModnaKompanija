using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ModnaKompanija_API.Models
{
    public class Narudzba
    {


        public Narudzba()
        {
           
            this.NarudzbaStavke = new HashSet<NarudzbaStavke>();
        }
        public int Id { get; set; }

        public Korisnici Korisnici { get; set; }
        public int KorisniciId { get; set; }
        public DateTime DatumNarudzbe { get; set; }
        public bool Status { get; set; }
        public decimal Iznos { get; set; }
        public Nullable<bool> Otkazano { get; set; }
        public virtual ICollection<NarudzbaStavke> NarudzbaStavke { get; set; }
    }
}