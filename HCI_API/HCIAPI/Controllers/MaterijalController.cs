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
    public class MaterijalController : ApiController
    {
        // GET: Materijal
        public class MaterijaliVM
        {
            public class MaterijaliInfo
            {


                public int Id { get; set; }

                public string Naziv { get; set; }
                public string Opis { get; set; }
                public decimal Cijena { get; set; }
                public string Sifra { get; set; }

             
            }
            public List<MaterijaliInfo> MaterijaliLista { get; set; }
        }


        [HttpGet]
        [Route("api/Materijal/GetMaterijale")]
        public MaterijaliVM GetMaterijale()
        {
            Context ctx = new Context();
            MaterijaliVM model = new MaterijaliVM();

            model.MaterijaliLista = ctx.Materijali.Select(x => new MaterijaliVM.MaterijaliInfo
            {
                Id = x.Id,
                 Naziv=x.Naziv,
                  Opis=x.Opis,
             
                Sifra = x.Sifra,
                Cijena = x.Cijena
                

            }).ToList();
            return model;
        }


    }

}