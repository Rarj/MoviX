import com.arj.common.utils.DateUtils
import com.arj.common.utils.ReleaseStatus
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.threeten.bp.LocalDate

@RunWith(JUnit4::class)
internal class DateUtilsTest {
    @Test
    fun `return RELEASED when release date is equal or smaller to today`() {
        assertTrue(
            ReleaseStatus.RELEASED == DateUtils.getReleaseStatus(
                releaseDate = "2025-01-19",
                currentLocalDate = LocalDate.of(2025, 1, 19),
            )
        )
        assertTrue(
            ReleaseStatus.RELEASED == DateUtils.getReleaseStatus(
                releaseDate = "2025-01-18",
                currentLocalDate = LocalDate.of(2025, 1, 19),
            )
        )
    }

    @Test
    fun `return UPCOMING when release date is greater to today`() {
        assertTrue(
            ReleaseStatus.UPCOMING == DateUtils.getReleaseStatus(
                releaseDate = "2025-01-19",
                currentLocalDate = LocalDate.of(2025, 1, 10),
            )
        )
    }

}
