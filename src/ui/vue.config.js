module.exports = {
    outputDir : "../main/resources/static",
    indexPath : "../static/index.html",
    devServer:{
        port:9091,
        proxy:"http://localhost:9090"
    }
}