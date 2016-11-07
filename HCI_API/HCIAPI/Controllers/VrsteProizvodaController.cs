using ModnaKompanija_API.DAL;
using ModnaKompanija_API.Models;
using System;

using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;

namespace ModnaKompanija_API.Controllers
{
    public class VrsteProizvodaController : ApiController
    {
        public class DodajVM
        {

            public int Id { get; set; }
            public string NazivProizvoda { get; set; }
            public int VrstaProizvodaId { get; set; }
            public string Kolekcija { get; set; }
            public string Boja { get; set; }
            public string Materijal { get; set; }
            public string Datum { get; set; }
            public string OpisProizvoda { get; set; }
        
        }
        public class ProizvodiVM
        {
            public List<proizvodi> _Proizvodi { get; set; }

            public class proizvodi
            {
                public int Id { get; set; }
                public string NazivProizvoda { get; set; }
                public int VrstaProizvodaId { get; set; }
                public string Kolekcija { get; set; }
                public string Boja { get; set; }
                public string Materijal { get; set; }
                public string Datum { get; set; }
                public string OpisProizvoda { get; set; }
            }

        }
        public class pomVM
        {
            public int Id { get; set; }
            public string NazivProizvoda { get; set; }
            public int VrstaProizvodaId { get; set; }
            public string Boja { get; set; }
            public string Materijal { get; set; }
            public string Datum { get; set; }

            public string Kolekcija { get; set; }
            public string OpisProizvoda { get; set; }
        }
        public class VrsteProizvodaVM
        {

            public List<Vrste> _VrsteProizvoda { get; set; }

            public class Vrste
            {
                public int Id { get; set; }
                public string NazivVrste { get; set; }
            }
        }

        Context ctx = new Context();
        public VrsteProizvodaVM Get()
        {

            VrsteProizvodaVM vrste = ctx.VrstaProizvoda.Select(x => new VrsteProizvodaVM
            {
                _VrsteProizvoda = ctx.VrstaProizvoda.Select(v => new VrsteProizvodaVM.Vrste
                {
                    Id = v.Id,
                    NazivVrste = v.NazivVrste
                }).ToList()
            }).First();

            return vrste;
        }
        [HttpGet]
        [Route("api/VrsteProizvoda/ProizvodiByVrsta/{vrsta}")]
        public ProizvodiVM ProizvodiByVrsta(string vrsta)
        {

            List<pomVM> listaPom = ctx.Proizvodi.Where(x => x.VrstaProizvoda.NazivVrste == vrsta).Select(x => new pomVM
            {
                Id = x.Id,
                 Boja=x.Boja,
                  Datum=x.Datum,
                   Materijal=x.Materijal,
                NazivProizvoda = x.NazivProizvoda,
                OpisProizvoda = x.OpisProizvoda,
                 Kolekcija=x.Kolekcija,
                VrstaProizvodaId = x.VrstaProizvodaId
            }).ToList();


            ProizvodiVM pom = new ProizvodiVM();
            pom._Proizvodi = listaPom.Select(p => new ProizvodiVM.proizvodi
            {
                Id = p.Id,
                 Boja=p.Boja,
                  Datum=p.Datum,
                   Kolekcija=p.Kolekcija,
                    Materijal=p.Materijal,
                NazivProizvoda = p.NazivProizvoda,
                OpisProizvoda = p.OpisProizvoda,
                
                VrstaProizvodaId = p.VrstaProizvodaId
            }).ToList();

            return pom;
        }


        [Route("api/VrsteProizvoda/NoviProizvod")]
        [ResponseType(typeof(DodajVM))]
        public IHttpActionResult NoviProizvod(DodajVM p)
        {
            Proizvodi proizvod = new Proizvodi();
            proizvod.NazivProizvoda = p.NazivProizvoda;
            proizvod.VrstaProizvodaId = p.VrstaProizvodaId;
            proizvod.Materijal = p.Materijal;
            proizvod.Datum = p.Datum;
            proizvod.Boja = p.Boja;
            proizvod.OpisProizvoda = p.OpisProizvoda;
            proizvod.Kolekcija = p.Kolekcija;
      

            try
            {
                ctx.Proizvodi.Add(proizvod);
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