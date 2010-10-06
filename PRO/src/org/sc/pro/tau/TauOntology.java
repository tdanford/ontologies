package org.sc.pro.tau;

import org.sc.obo.annotations.*;

public class TauOntology {
}

@Term
@ImportedFrom("CheBI")
@ID("CHEBI:32958")
interface PhosphateGroup { 
}

@Term
@ImportedFrom("SO")
@ID("SO:0000195")
@Name("coding_exon")
@Definition("\"An exon whereby at least one base is part of a codon (here, 'codon' is inclusive of the stop_codon).\"")
interface CodingExon { 
}

@Term
@ID("CHEBI:33708")
@ImportedFrom("CheBI")
@Name("amino-acid residue")
interface AminoAcidResidue { 
}

@Term
@ID("CHEBI:32874")
@ImportedFrom("CheBI")
interface ProlineResidue extends AminoAcidResidue { 
}

@Term
@ID("CHEBI:32630")
@ImportedFrom("CheBI")
interface LeucineResidue extends AminoAcidResidue { 
}

@Term
@ImportedFrom("MOD")
@ID("MOD:00696")
interface PhosphorylatedResidue {
}

@Term
@ImportedFrom("SO")
@ID("SO:0001606")
@Name("amino_acid_substitution")
@Definition("\"A sequence variant of a codon resulting in the substitution of one amino acid " +
			"for another in the resulting polypeptide.\"")
interface AminoAcidSubstitution { 
}


@Term 
@ImportedFrom("PRO")
@ID("PRO:000000001")
interface Protein { 
}

@Term
@ImportedFrom("PRO")
@ID("PRO:000010173")
@Name("microtubule-associated protein tau")
@Definition("\"A protein that is a translation product of the MAPT gene or a 1:1 ortholog thereof.\" [PRO:DNx]")
@ExactSynonyms({ 
	"neurofibrillary tangle protein",
	"paired helical filament-tau",
	"PHF-tau",
})
@RelatedSynonyms({  
	"MAPT",
	"MAPTL",
	"MTBT1",
	"TAU",
	"Mtapt",
})
@Comment("Category=gene")
interface Tau extends Protein { 
}

@Extant
@Term
@ID("organism")
interface Organism { 
	
}

@Extant
@Term
@ID("human") 
interface Human extends Organism { 
	
}

@ID("REQUEST:0001")
@Name("microtubule-associated protein tau (human)")
@Definition("\"A protein that is a translation product of the human MAPT gene.\"")
@Defining(Tau.class)
@Term interface HumanTau extends Tau { 

	@Defining @Relates("only_in")
	public Human organism();
	
	@Relates("has_part")
	public HumanMAPTExonPart[] exons();
}

@Term
@ImportedFrom("PRO")
@ID("PRO:000018263")
interface AminoAcidChain { 
}

@ID("REQUEST:0002")
@Definition("\"A part of a protein that is an amino acid chain, and that is translated from a " +
			"single exon of an transcript derived from a single gene locus.\"")
@Defining(AminoAcidChain.class)
@Term interface ProteinExonPart extends AminoAcidChain { 
	
	@Defining @Relates("part_of")
	public Protein protein();
	
	@Defining @Relates("templated_from_bearer_of")
	public CodingExon codingExon();	
}

@ImportedFrom("SNAP")
@ID("snap:SITE")
@Term interface Site { 
}

@ID("REQUEST:0003")
@Definition("\"A site consisting of a region of a protein, defined in terms of the protein's " +
			"amino acid sequence or structural features.\"")
@Comment("\"A site consisting of a region of a protein, defined in terms of the protein's amino " +
		"acid sequence or structural features.\"")
@Defining(is_a = { Protein.class, Site.class })
@Term interface ProteinSite extends Site, Protein { 	
	
	@Defining @Relates("part_of")
	public Protein siteOn();
}

@ID("REQUEST:0008")
@Term interface HumanMAPTExonPart extends ProteinExonPart { 
}

@ID("REQUEST:0003")
@Term interface MAPTExonPart2 extends HumanMAPTExonPart { 
}

@ID("REQUEST:0004")
@Term interface MAPTExonPart5 extends HumanMAPTExonPart { 
}

@ID("REQUEST:0005")
@Term interface MAPTExonPart10 extends HumanMAPTExonPart { 
}

@ID("REQUEST:0007")
@Term interface MAPTExonPart3 extends HumanMAPTExonPart { 
}

@ID("REQUEST:0006")
@Name("microtubule-associated protein tau (human) reference isoform")
@Comment("Reference isoform, from which all other coordinates are calculated.")
@Term interface ReferenceTau extends HumanTau { 


	@Relates("has_part")
	public MAPTExonPart2 exon2();

	@Relates("has_part")
	public MAPTExonPart3 exon3();

	@Relates("has_part")
	public MAPTExonPart5 exon5();

	@Relates("has_part")
	public MAPTExonPart10 exon10();
}

@ID("REQUEST:0009")
@Name("microtubule-associated protein tau (human) isoform N1 R4")
@Definition("\"A microtubule-associated protein tau (human) isoform that lacks the 3rd exon part of the full-length form.\"")
@Comment("Category=isoform")
@Defining(HumanTau.class)
@Lacks(rel="has_part",term=MAPTExonPart3.class, defining=true)
@Term interface HumanTauN1R4 extends HumanTau { 	
}

