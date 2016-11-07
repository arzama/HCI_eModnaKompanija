using ModnaKompanija_API.DAL;

using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ModnaKompanija_API.Controllers
{
    public class AutentifikacijaController : ApiController
    {
        public class KorisniciVM
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
        [HttpGet]
        [Route("api/Autentifikacija/Provjera/{username}/{lozinka}")]
        public KorisniciVM Provjera(string username, string lozinka)
        {
            Context ctx = new Context();
            KorisniciVM k = ctx.Korisnici.Where(x => x.KorisnickoIme == username && x.Lozinka == lozinka).Select(x => new KorisniciVM
            {
                Ime = x.Ime,
                Prezime = x.Prezime,
                Email=x.Email,
                 Adresa=x.Adresa,
                   Kontakt=x.Kontakt,
                KorisnickoIme = x.KorisnickoIme,
                 Id=x.Id,
                Lozinka = x.Lozinka
            }).FirstOrDefault();

            return k;
        }
    }
}
