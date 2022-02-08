package homework.chegg.com.chegghomework.network

/**
 * Created by seladev
 */
data class DataBDto(val metadata:Metadata) {}

data class Metadata(val innerdata:List<InnerData>)
{}

data class InnerData(val aticleId: Int,
                     val articlewrapper: ArticleWrapper,
                     val picture: String)
{}

data class ArticleWrapper(val header: String,
                     val description: String)
{}