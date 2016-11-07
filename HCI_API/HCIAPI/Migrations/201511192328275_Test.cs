namespace ModnaKompanija_API.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class Test : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Korisnicis",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Ime = c.String(),
                        Prezime = c.String(),
                        Email = c.String(),
                        Adresa = c.String(),
                        Kontakt = c.String(),
                        KorisnickoIme = c.String(),
                        Lozinka = c.String(),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.Materijalis",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Naziv = c.String(),
                        Opis = c.String(),
                        Cijena = c.Decimal(nullable: false, precision: 18, scale: 2),
                        Sifra = c.String(),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.NarudzbaStavkes",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        MaterijaliId = c.Int(nullable: false),
                        NarudzbaId = c.Int(nullable: false),
                        Kolicina = c.Int(nullable: false),
                        CijenaProizvoda = c.Decimal(nullable: false, precision: 18, scale: 2),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Materijalis", t => t.MaterijaliId, cascadeDelete: true)
                .ForeignKey("dbo.Narudzbas", t => t.NarudzbaId, cascadeDelete: true)
                .Index(t => t.MaterijaliId)
                .Index(t => t.NarudzbaId);
            
            CreateTable(
                "dbo.Narudzbas",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        BrojNarudzbe = c.Int(nullable: false),
                        KorisniciId = c.Int(nullable: false),
                        DatumNarudzbe = c.DateTime(nullable: false),
                        Status = c.Boolean(nullable: false),
                        Iznos = c.Decimal(nullable: false, precision: 18, scale: 2),
                        Otkazano = c.Boolean(),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Korisnicis", t => t.KorisniciId, cascadeDelete: true)
                .Index(t => t.KorisniciId);
            
            CreateTable(
                "dbo.Ocjenes",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Ocjena = c.Double(nullable: false),
                        ProizvodId = c.Int(nullable: false),
                        KorisnikId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Korisnicis", t => t.KorisnikId, cascadeDelete: true)
                .ForeignKey("dbo.Proizvodis", t => t.ProizvodId, cascadeDelete: true)
                .Index(t => t.ProizvodId)
                .Index(t => t.KorisnikId);
            
            CreateTable(
                "dbo.Proizvodis",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        NazivProizvoda = c.String(),
                        VrstaProizvodaId = c.Int(nullable: false),
                        Boja = c.String(),
                        Materijal = c.String(),
                        Datum = c.String(),
                        OpisProizvoda = c.String(),
                        Kolekcija = c.String(),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.VrstaProizvodas", t => t.VrstaProizvodaId, cascadeDelete: true)
                .Index(t => t.VrstaProizvodaId);
            
            CreateTable(
                "dbo.VrstaProizvodas",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        NazivVrste = c.String(),
                    })
                .PrimaryKey(t => t.Id);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Ocjenes", "ProizvodId", "dbo.Proizvodis");
            DropForeignKey("dbo.Proizvodis", "VrstaProizvodaId", "dbo.VrstaProizvodas");
            DropForeignKey("dbo.Ocjenes", "KorisnikId", "dbo.Korisnicis");
            DropForeignKey("dbo.NarudzbaStavkes", "NarudzbaId", "dbo.Narudzbas");
            DropForeignKey("dbo.Narudzbas", "KorisniciId", "dbo.Korisnicis");
            DropForeignKey("dbo.NarudzbaStavkes", "MaterijaliId", "dbo.Materijalis");
            DropIndex("dbo.Proizvodis", new[] { "VrstaProizvodaId" });
            DropIndex("dbo.Ocjenes", new[] { "KorisnikId" });
            DropIndex("dbo.Ocjenes", new[] { "ProizvodId" });
            DropIndex("dbo.Narudzbas", new[] { "KorisniciId" });
            DropIndex("dbo.NarudzbaStavkes", new[] { "NarudzbaId" });
            DropIndex("dbo.NarudzbaStavkes", new[] { "MaterijaliId" });
            DropTable("dbo.VrstaProizvodas");
            DropTable("dbo.Proizvodis");
            DropTable("dbo.Ocjenes");
            DropTable("dbo.Narudzbas");
            DropTable("dbo.NarudzbaStavkes");
            DropTable("dbo.Materijalis");
            DropTable("dbo.Korisnicis");
        }
    }
}
