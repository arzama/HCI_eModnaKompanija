using ModnaKompanija_API.DAL;
using ModnaKompanija_API.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;

namespace ModnaKompanija_API.Controllers
{
    public class KorisnikController : ApiController
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
        Context ctx = new Context();
        [HttpPost]
        [Route("api/Korisnik/IzmjeniPodatke")]
      public IHttpActionResult IzmjeniPodatke(KorisniciVM k)
        {
            Korisnici korisnik = new Korisnici();
            korisnik.Id = k.Id;
            korisnik.Ime = k.Ime;
            korisnik.Prezime = k.Prezime;
            korisnik.Email = k.Email;
            korisnik.Adresa = k.Adresa;
            korisnik.Kontakt = k.Kontakt;
            korisnik.KorisnickoIme = k.KorisnickoIme;
            korisnik.Lozinka = k.Lozinka;

           
                ctx.Entry(korisnik).State = EntityState.Modified;
                ctx.SaveChanges();
            
      
            return Ok();
        }

        private bool KorisnikExists(int id)
        {
            return ctx.Korisnici.Count(e => e.Id == id) > 0;
        }


        [Route("api/Korisnik/NoviKorisnik")]
        [ResponseType(typeof(KorisniciVM))]
        public IHttpActionResult NoviKorisnik(KorisniciVM k)
        {
            Korisnici korisnik = new Korisnici();
            korisnik.Email = k.Email;
            korisnik.Adresa = k.Adresa;
            korisnik.Kontakt = k.Kontakt;
            korisnik.Ime = k.Ime;
            korisnik.KorisnickoIme = k.KorisnickoIme;
            korisnik.Lozinka = k.Lozinka;
            korisnik.Prezime = k.Prezime;

            try
            {
                ctx.Korisnici.Add(korisnik);
                ctx.SaveChanges();
            }
            catch (Exception)
            {

                return NotFound();
            }



            return Ok();
        }
    }
}