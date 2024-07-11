import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import Week2.Wednesday.TaxCalculator

class TaxCalculatorSpec extends AnyWordSpec {

  val taxCalculator: TaxCalculator = new TaxCalculator

  "TaxCalculator.calculateTax" should {
    "return the total amount of tax to pay" when {
      "the income is below the personal tax limit" in {
        val result: Double = taxCalculator.calculateTax(5000)
        assert(result == 0)
      }

      "the income is exactly at the personal tax limit" in {
        val result: Double = taxCalculator.calculateTax(10000)
        assert(result == 0)
      }

      "the income is within the basic rate limit" in {
        val result: Double = taxCalculator.calculateTax(30000)
        assert(result == 4000) // (30000 - 10000) * 0.2
      }

      "the income is within the higher rate limit" in {
        val result: Double = taxCalculator.calculateTax(100000)
        assert(result == 28000) // Corrected expected value
      }

      "the income is within the additional rate limit" in {
        val result: Double = taxCalculator.calculateTax(150000)
        assert(result == 49250) // Corrected expected value
      }
    }
  }

  "TaxCalculator.isHigherRateTaxpayer" should {
    "return true if income is within the higher rate limit" in {
      val result: Boolean = taxCalculator.isHigherRateTaxpayer(100000)
      assert(result)
    }

    "return false if income is within the basic rate limit" in {
      val result: Boolean = taxCalculator.isHigherRateTaxpayer(30000)
      assert(!result)
    }
  }

  "TaxCalculator.formattedCurrentTaxAllowance" should {
    "return formatted string for personal allowance" in {
      val result: String = taxCalculator.formattedCurrentTaxAllowance(5000)
      assert(result == "£10,000")
    }

    "return formatted string for basic rate limit" in {
      val result: String = taxCalculator.formattedCurrentTaxAllowance(30000)
      assert(result == "£50,000")
    }

    "return formatted string for higher rate limit" in {
      val result: String = taxCalculator.formattedCurrentTaxAllowance(100000)
      assert(result == "£125,000")
    }

    "return 'No limit' for additional rate" in {
      val result: String = taxCalculator.formattedCurrentTaxAllowance(150000)
      assert(result == "No limit")
    }
  }
}
