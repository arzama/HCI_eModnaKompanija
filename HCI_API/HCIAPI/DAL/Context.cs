using ModnaKompanija_API.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace ModnaKompanija_API.DAL
{
    public class Context:DbContext
    {
        public Context()
            : base("ConnectionString")
        { }


        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
        }
     public DbSet<Korisnici> Korisnici { get; set; }
     public DbSet<Proizvodi> Proizvodi { get; set; }
     public DbSet<Narudzba> Narudzbe { get; set; }
     public DbSet<Materijali> Materijali { get; set; }
     public DbSet<NarudzbaStavke> NarudzbaStavka { get; set; }
     public DbSet<Ocjene> Ocjene { get; set; }
     public DbSet<VrstaProizvoda> VrstaProizvoda { get; set; }


    }
}