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
    public class NarudzbaController : ApiController{
 

       


        public class ListaNarudzbiVM
        {
            public class NarudzbeInfo
            {
                public int NarudzbaID { get; set; }
             
                public int KorisnikId { get; set; }
                public DateTime Datum { get; set; }
                public List<NarudzbaStavkeInfo> StavkeList { get; set; }
            }

            public class NarudzbaStavkeInfo
            {
                public int NarudzbaStavkaID { get; set; }
                public int NarudzbaID { get; set; }
                public int Kolicina { get; set; }
                public decimal CijenaMaterijala{ get; set; }
                public string NazivMaterijala { get; set; }
            }
            public List<NarudzbeInfo> NarudzbeList { get; set; }
        }



        public class NarudzbaVM
        {
            public int NarudzbaID { get; set; }
            public int Kolicina { get; set; }
           
            public double Iznos { get; set; }
            public DateTime Datum { get; set; }
            public int KorisnikId { get; set; }
        }

        public class NarudzbaNarudzbaStavkeVM
        {
           public string NazivMaterijala { get; set; }  
            public int MaterijalId { get; set; }

           public double Cijena { get; set; }     
            public int kolicina { get; set; }
        }


      

        // GET: Narudzba
        Context ctx = new Context();
        [HttpGet]
        [Route("api/Narudzba/GetNarudzbeSaStavkama/{korisnikId}")]
        public ListaNarudzbiVM GetNarudzbeSaStavkama(int korisnikId)
        {

            ListaNarudzbiVM model = new ListaNarudzbiVM();

            model.NarudzbeList = ctx.Narudzbe.Where(z => z.KorisniciId == korisnikId).Select(z => new ListaNarudzbiVM.NarudzbeInfo
            {
                NarudzbaID = z.Id,
                
              
                KorisnikId = z.KorisniciId,
                Datum = z.DatumNarudzbe,
                StavkeList = z.NarudzbaStavke.Where(x => x.NarudzbaId == z.Id).Select(x => new ListaNarudzbiVM.NarudzbaStavkeInfo
                {
                    NarudzbaStavkaID = x.Id,
                    NarudzbaID = x.NarudzbaId,
                   NazivMaterijala = x.Materijali.Naziv,    
                    Kolicina = x.Kolicina,

                   CijenaMaterijala = x.Materijali.Cijena    

                }).ToList()
            }).ToList();

            return model;
        }

      

        [HttpPost]    
        [Route("api/Narudzba/Post")]
        
        public IHttpActionResult Post(NarudzbaVM novaNarudzba)    
        {
            Narudzba n = new Narudzba();

        
            n.DatumNarudzbe = DateTime.Now;
            n.Iznos = (decimal)novaNarudzba.Iznos;
            n.KorisniciId = novaNarudzba.KorisnikId;
            n.Otkazano = true;
            
      
           
                ctx.Narudzbe.Add(n);
                ctx.SaveChanges();
         
         
            

            return Ok();
        }


        [Route("api/Narudzba/DodajStavke")]
        [ResponseType(typeof(NarudzbaNarudzbaStavkeVM))]
        public IHttpActionResult DodajStavke(List<NarudzbaNarudzbaStavkeVM> stavke)
        {
            foreach (NarudzbaNarudzbaStavkeVM item in stavke)
            {

                NarudzbaStavke n = new NarudzbaStavke();
                n.MaterijaliId = item.MaterijalId;
                n.NarudzbaId = ctx.Narudzbe.OrderByDescending(x => x.Id).Select(x => x.Id).FirstOrDefault();
                n.Kolicina = item.kolicina;
                
               
                ctx.NarudzbaStavka.Add(n);
            }


            ctx.SaveChanges();


            return Ok();
        }
    }
}