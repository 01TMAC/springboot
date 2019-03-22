package com.test.spring.boot.blog.mahout_test;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

import java.io.File;

public class TrueTest {
    public static void main(String[] args) throws Exception {
        TrueTest tt = new TrueTest();
        tt.test();
    }
    public void test() throws  Exception{
        RandomUtils.useTestSeed();
        String url = (this.getClass().getClassLoader().getResource("mahout/intro.csv")).toString();
//                String url = "/src/intro.csv";
        url = url.substring(6);
        String urlUTF8 = java.net.URLDecoder.decode(url, "utf-8");
        System.out.println("url:"+url);
        System.out.println("url:"+urlUTF8);

        DataModel dataModel = new FileDataModel(new File(urlUTF8));
        RecommenderIRStatsEvaluator evaluator = new GenericRecommenderIRStatsEvaluator();
        //用于生成推荐引擎的构建器
        RecommenderBuilder builder = new RecommenderBuilder() {

            public Recommender buildRecommender(DataModel model) throws TasteException {
                // TODO Auto-generated method stub
                //用户相似度，多种方法
                ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
                //用户邻居
                //UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
                //一个推荐器
                return new GenericItemBasedRecommender(model,similarity);
            }
        };
        //评估推荐2个结果时的查准率和查全率
        IRStatistics statistics = evaluator.evaluate(builder, null, dataModel, null, 2, GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, 1.0);

        System.out.println("查准率："+statistics.getPrecision());
        System.out.println("查全率："+statistics.getRecall());
    }
}
