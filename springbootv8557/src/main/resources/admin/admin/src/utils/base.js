const base = {
    get() {
        return {
            url: "http://localhost:8080/zhiguo/",
            name: "zhiguo",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/zhiguo/front/dist/index.html'
        };
    },
    getProjectName() {
        return {
            projectName: "智果易联"
        }
    }
}
export default base