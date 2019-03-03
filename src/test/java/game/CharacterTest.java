package game;

import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;

import springboot.domain.Ideal;
import springboot.domain.Player;
import springboot.util.AnalysisResult;
import springboot.util.PlayerConfig;

public class CharacterTest {
	public static void main(String[] args) {
		AnalysisResult analysisResult = new AnalysisResult();
		analysisResult.setJsonObject("{\n" + 
				"  \"word_count\": 165,\n" + 
				"  \"word_count_message\": \"There were 165 words in the input. We need a minimum of 600, preferably 1,200 or more, to compute statistically significant estimates\",\n" + 
				"  \"processed_language\": \"en\",\n" + 
				"  \"personality\": [\n" + 
				"    {\n" + 
				"      \"trait_id\": \"big5_openness\",\n" + 
				"      \"name\": \"Openness\",\n" + 
				"      \"category\": \"personality\",\n" + 
				"      \"percentile\": 0.9813232828016983,\n" + 
				"      \"significant\": true,\n" + 
				"      \"children\": [\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_adventurousness\",\n" + 
				"          \"name\": \"Adventurousness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.4946358521304755,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_artistic_interests\",\n" + 
				"          \"name\": \"Artistic interests\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.9886335167649051,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_emotionality\",\n" + 
				"          \"name\": \"Emotionality\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.6880873476166887,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_imagination\",\n" + 
				"          \"name\": \"Imagination\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.9380365393628683,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_intellect\",\n" + 
				"          \"name\": \"Intellect\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.9978544823671867,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_liberalism\",\n" + 
				"          \"name\": \"Authority-challenging\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.9818027030877472,\n" + 
				"          \"significant\": true\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"big5_conscientiousness\",\n" + 
				"      \"name\": \"Conscientiousness\",\n" + 
				"      \"category\": \"personality\",\n" + 
				"      \"percentile\": 0.07841739643332013,\n" + 
				"      \"significant\": true,\n" + 
				"      \"children\": [\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_achievement_striving\",\n" + 
				"          \"name\": \"Achievement striving\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.21607349772629053,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_cautiousness\",\n" + 
				"          \"name\": \"Cautiousness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.8757127320130038,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_dutifulness\",\n" + 
				"          \"name\": \"Dutifulness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.34387983545564754,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_orderliness\",\n" + 
				"          \"name\": \"Orderliness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.2930758902132277,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_self_discipline\",\n" + 
				"          \"name\": \"Self-discipline\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.1530261246458049,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_self_efficacy\",\n" + 
				"          \"name\": \"Self-efficacy\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.24863199210233022,\n" + 
				"          \"significant\": true\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"big5_extraversion\",\n" + 
				"      \"name\": \"Extraversion\",\n" + 
				"      \"category\": \"personality\",\n" + 
				"      \"percentile\": 0.3664542802874138,\n" + 
				"      \"significant\": true,\n" + 
				"      \"children\": [\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_activity_level\",\n" + 
				"          \"name\": \"Activity level\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.19052285565648652,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_assertiveness\",\n" + 
				"          \"name\": \"Assertiveness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.6095324834771465,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_cheerfulness\",\n" + 
				"          \"name\": \"Cheerfulness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.12386933471130468,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_excitement_seeking\",\n" + 
				"          \"name\": \"Excitement-seeking\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.34792864016849817,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_friendliness\",\n" + 
				"          \"name\": \"Outgoing\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.027998764520893005,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_gregariousness\",\n" + 
				"          \"name\": \"Gregariousness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.03391884671249257,\n" + 
				"          \"significant\": true\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"big5_agreeableness\",\n" + 
				"      \"name\": \"Agreeableness\",\n" + 
				"      \"category\": \"personality\",\n" + 
				"      \"percentile\": 0.004237733610231498,\n" + 
				"      \"significant\": true,\n" + 
				"      \"children\": [\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_altruism\",\n" + 
				"          \"name\": \"Altruism\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.4889331766354704,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_cooperation\",\n" + 
				"          \"name\": \"Cooperation\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.1880939669821533,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_modesty\",\n" + 
				"          \"name\": \"Modesty\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.05749919299647055,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_morality\",\n" + 
				"          \"name\": \"Uncompromising\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.24459106771645706,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_sympathy\",\n" + 
				"          \"name\": \"Sympathy\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.821985143891679,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_trust\",\n" + 
				"          \"name\": \"Trust\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.1815486466078185,\n" + 
				"          \"significant\": true\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"big5_neuroticism\",\n" + 
				"      \"name\": \"Emotional range\",\n" + 
				"      \"category\": \"personality\",\n" + 
				"      \"percentile\": 0.29316069172191184,\n" + 
				"      \"significant\": true,\n" + 
				"      \"children\": [\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_anger\",\n" + 
				"          \"name\": \"Fiery\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.6314449427384663,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_anxiety\",\n" + 
				"          \"name\": \"Prone to worry\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.5307697226611907,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_depression\",\n" + 
				"          \"name\": \"Melancholy\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.6068853753087824,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_immoderation\",\n" + 
				"          \"name\": \"Immoderation\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.7071575428039127,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_self_consciousness\",\n" + 
				"          \"name\": \"Self-consciousness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.5921361102191058,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_vulnerability\",\n" + 
				"          \"name\": \"Susceptible to stress\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.4003754316480051,\n" + 
				"          \"significant\": true\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"needs\": [\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_challenge\",\n" + 
				"      \"name\": \"Challenge\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.06985224594203587,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_closeness\",\n" + 
				"      \"name\": \"Closeness\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.10379755059398871,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_curiosity\",\n" + 
				"      \"name\": \"Curiosity\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.7508260275640644,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_excitement\",\n" + 
				"      \"name\": \"Excitement\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.22089397501595526,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_harmony\",\n" + 
				"      \"name\": \"Harmony\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.09850327603792797,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_ideal\",\n" + 
				"      \"name\": \"Ideal\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.5725713409635671,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_liberty\",\n" + 
				"      \"name\": \"Liberty\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.15043629949328252,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_love\",\n" + 
				"      \"name\": \"Love\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.39768226461097206,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_practicality\",\n" + 
				"      \"name\": \"Practicality\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.1516191955858795,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_self_expression\",\n" + 
				"      \"name\": \"Self-expression\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.45652271303594744,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_stability\",\n" + 
				"      \"name\": \"Stability\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.5834433156134613,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_structure\",\n" + 
				"      \"name\": \"Structure\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.19560001270691635,\n" + 
				"      \"significant\": true\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"values\": [\n" + 
				"    {\n" + 
				"      \"trait_id\": \"value_conservation\",\n" + 
				"      \"name\": \"Conservation\",\n" + 
				"      \"category\": \"values\",\n" + 
				"      \"percentile\": 0.046773852344503986,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"value_openness_to_change\",\n" + 
				"      \"name\": \"Openness to change\",\n" + 
				"      \"category\": \"values\",\n" + 
				"      \"percentile\": 0.5835576699717313,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"value_hedonism\",\n" + 
				"      \"name\": \"Hedonism\",\n" + 
				"      \"category\": \"values\",\n" + 
				"      \"percentile\": 0.21853768794272088,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"value_self_enhancement\",\n" + 
				"      \"name\": \"Self-enhancement\",\n" + 
				"      \"category\": \"values\",\n" + 
				"      \"percentile\": 0.5791067559891167,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"value_self_transcendence\",\n" + 
				"      \"name\": \"Self-transcendence\",\n" + 
				"      \"category\": \"values\",\n" + 
				"      \"percentile\": 0.29353680488361106,\n" + 
				"      \"significant\": true\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"behavior\": [\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_sunday\",\n" + 
				"      \"name\": \"Sunday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.8181818181818182\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_monday\",\n" + 
				"      \"name\": \"Monday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_tuesday\",\n" + 
				"      \"name\": \"Tuesday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_wednesday\",\n" + 
				"      \"name\": \"Wednesday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_thursday\",\n" + 
				"      \"name\": \"Thursday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_friday\",\n" + 
				"      \"name\": \"Friday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_saturday\",\n" + 
				"      \"name\": \"Saturday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.18181818181818182\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0000\",\n" + 
				"      \"name\": \"0:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0100\",\n" + 
				"      \"name\": \"1:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0200\",\n" + 
				"      \"name\": \"2:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0300\",\n" + 
				"      \"name\": \"3:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0400\",\n" + 
				"      \"name\": \"4:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0500\",\n" + 
				"      \"name\": \"5:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0600\",\n" + 
				"      \"name\": \"6:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0700\",\n" + 
				"      \"name\": \"7:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0800\",\n" + 
				"      \"name\": \"8:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0900\",\n" + 
				"      \"name\": \"9:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1000\",\n" + 
				"      \"name\": \"10:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1100\",\n" + 
				"      \"name\": \"11:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1200\",\n" + 
				"      \"name\": \"12:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1300\",\n" + 
				"      \"name\": \"1:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1400\",\n" + 
				"      \"name\": \"2:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1500\",\n" + 
				"      \"name\": \"3:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.8181818181818182\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1600\",\n" + 
				"      \"name\": \"4:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.18181818181818182\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1700\",\n" + 
				"      \"name\": \"5:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1800\",\n" + 
				"      \"name\": \"6:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1900\",\n" + 
				"      \"name\": \"7:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_2000\",\n" + 
				"      \"name\": \"8:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_2100\",\n" + 
				"      \"name\": \"9:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_2200\",\n" + 
				"      \"name\": \"10:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_2300\",\n" + 
				"      \"name\": \"11:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"consumption_preferences\": [\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_shopping\",\n" + 
				"      \"name\": \"Purchasing Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_automobile_ownership_cost\",\n" + 
				"          \"name\": \"Likely to be sensitive to ownership cost when buying automobiles\",\n" + 
				"          \"score\": 0.5\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_automobile_safety\",\n" + 
				"          \"name\": \"Likely to prefer safety when buying automobiles\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_clothes_quality\",\n" + 
				"          \"name\": \"Likely to prefer quality when buying clothes\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_clothes_style\",\n" + 
				"          \"name\": \"Likely to prefer style when buying clothes\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_clothes_comfort\",\n" + 
				"          \"name\": \"Likely to prefer comfort when buying clothes\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_influence_brand_name\",\n" + 
				"          \"name\": \"Likely to be influenced by brand name when making product purchases\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_influence_utility\",\n" + 
				"          \"name\": \"Likely to be influenced by product utility when making product purchases\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_influence_online_ads\",\n" + 
				"          \"name\": \"Likely to be influenced by online ads when making product purchases\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_influence_social_media\",\n" + 
				"          \"name\": \"Likely to be influenced by social media when making product purchases\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_influence_family_members\",\n" + 
				"          \"name\": \"Likely to be influenced by family when making product purchases\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_spur_of_moment\",\n" + 
				"          \"name\": \"Likely to indulge in spur of the moment purchases\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_credit_card_payment\",\n" + 
				"          \"name\": \"Likely to prefer using credit cards for shopping\",\n" + 
				"          \"score\": 0\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_health_and_activity\",\n" + 
				"      \"name\": \"Health & Activity Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_eat_out\",\n" + 
				"          \"name\": \"Likely to eat out frequently\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_gym_membership\",\n" + 
				"          \"name\": \"Likely to have a gym membership\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_outdoor\",\n" + 
				"          \"name\": \"Likely to like outdoor activities\",\n" + 
				"          \"score\": 0.5\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_environmental_concern\",\n" + 
				"      \"name\": \"Environmental Concern Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_concerned_environment\",\n" + 
				"          \"name\": \"Likely to be concerned about the environment\",\n" + 
				"          \"score\": 1\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_entrepreneurship\",\n" + 
				"      \"name\": \"Entrepreneurship Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_start_business\",\n" + 
				"          \"name\": \"Likely to consider starting a business in next few years\",\n" + 
				"          \"score\": 0\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_movie\",\n" + 
				"      \"name\": \"Movie Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_romance\",\n" + 
				"          \"name\": \"Likely to like romance movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_adventure\",\n" + 
				"          \"name\": \"Likely to like adventure movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_horror\",\n" + 
				"          \"name\": \"Likely to like horror movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_musical\",\n" + 
				"          \"name\": \"Likely to like musical movies\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_historical\",\n" + 
				"          \"name\": \"Likely to like historical movies\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_science_fiction\",\n" + 
				"          \"name\": \"Likely to like science-fiction movies\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_war\",\n" + 
				"          \"name\": \"Likely to like war movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_drama\",\n" + 
				"          \"name\": \"Likely to like drama movies\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_action\",\n" + 
				"          \"name\": \"Likely to like action movies\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_documentary\",\n" + 
				"          \"name\": \"Likely to like documentary movies\",\n" + 
				"          \"score\": 1\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_music\",\n" + 
				"      \"name\": \"Music Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_rap\",\n" + 
				"          \"name\": \"Likely to like rap music\",\n" + 
				"          \"score\": 0.5\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_country\",\n" + 
				"          \"name\": \"Likely to like country music\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_r_b\",\n" + 
				"          \"name\": \"Likely to like R&B music\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_hip_hop\",\n" + 
				"          \"name\": \"Likely to like hip hop music\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_live_event\",\n" + 
				"          \"name\": \"Likely to attend live musical events\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_playing\",\n" + 
				"          \"name\": \"Likely to have experience playing music\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_latin\",\n" + 
				"          \"name\": \"Likely to like Latin music\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_rock\",\n" + 
				"          \"name\": \"Likely to like rock music\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_classical\",\n" + 
				"          \"name\": \"Likely to like classical music\",\n" + 
				"          \"score\": 1\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_reading\",\n" + 
				"      \"name\": \"Reading Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_read_frequency\",\n" + 
				"          \"name\": \"Likely to read often\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_books_entertainment_magazines\",\n" + 
				"          \"name\": \"Likely to read entertainment magazines\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_books_non_fiction\",\n" + 
				"          \"name\": \"Likely to read non-fiction books\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_books_financial_investing\",\n" + 
				"          \"name\": \"Likely to read financial investment books\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_books_autobiographies\",\n" + 
				"          \"name\": \"Likely to read autobiographical books\",\n" + 
				"          \"score\": 1\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_volunteering\",\n" + 
				"      \"name\": \"Volunteering Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_volunteer\",\n" + 
				"          \"name\": \"Likely to volunteer for social causes\",\n" + 
				"          \"score\": 1\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"warnings\": [\n" + 
				"    {\n" + 
				"      \"warning_id\": \"WORD_COUNT_MESSAGE\",\n" + 
				"      \"message\": \"There were 165 words in the input. We need a minimum of 600, preferably 1,200 or more, to compute statistically significant estimates\"\n" + 
				"    }\n" + 
				"  ]\n" + 
				"}");
		
		String str = "{\n" + 
				"  \"word_count\": 13906,\n" + 
				"  \"processed_language\": \"en\",\n" + 
				"  \"personality\": [\n" + 
				"    {\n" + 
				"      \"trait_id\": \"big5_openness\",\n" + 
				"      \"name\": \"Openness\",\n" + 
				"      \"category\": \"personality\",\n" + 
				"      \"percentile\": 0.37719285142427744,\n" + 
				"      \"significant\": true,\n" + 
				"      \"children\": [\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_adventurousness\",\n" + 
				"          \"name\": \"Adventurousness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.6269902936062156,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_artistic_interests\",\n" + 
				"          \"name\": \"Artistic interests\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.36502449340082244,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_emotionality\",\n" + 
				"          \"name\": \"Emotionality\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.575277122861643,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_imagination\",\n" + 
				"          \"name\": \"Imagination\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.6481685797103642,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_intellect\",\n" + 
				"          \"name\": \"Intellect\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.26645540995219036,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_liberalism\",\n" + 
				"          \"name\": \"Authority-challenging\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.41616860883248574,\n" + 
				"          \"significant\": true\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"big5_conscientiousness\",\n" + 
				"      \"name\": \"Conscientiousness\",\n" + 
				"      \"category\": \"personality\",\n" + 
				"      \"percentile\": 0.757494596417116,\n" + 
				"      \"significant\": true,\n" + 
				"      \"children\": [\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_achievement_striving\",\n" + 
				"          \"name\": \"Achievement striving\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.626132731305318,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_cautiousness\",\n" + 
				"          \"name\": \"Cautiousness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.6480554261936662,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_dutifulness\",\n" + 
				"          \"name\": \"Dutifulness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.40733337460093694,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_orderliness\",\n" + 
				"          \"name\": \"Orderliness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.7503536559594107,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_self_discipline\",\n" + 
				"          \"name\": \"Self-discipline\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.7469491071859633,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_self_efficacy\",\n" + 
				"          \"name\": \"Self-efficacy\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.5294511637795724,\n" + 
				"          \"significant\": true\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"big5_extraversion\",\n" + 
				"      \"name\": \"Extraversion\",\n" + 
				"      \"category\": \"personality\",\n" + 
				"      \"percentile\": 0.7050144929885472,\n" + 
				"      \"significant\": true,\n" + 
				"      \"children\": [\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_activity_level\",\n" + 
				"          \"name\": \"Activity level\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.5873567314350562,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_assertiveness\",\n" + 
				"          \"name\": \"Assertiveness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.6318532123843208,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_cheerfulness\",\n" + 
				"          \"name\": \"Cheerfulness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.8605364160670006,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_excitement_seeking\",\n" + 
				"          \"name\": \"Excitement-seeking\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.5690047072868112,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_friendliness\",\n" + 
				"          \"name\": \"Outgoing\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.8022556395101721,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_gregariousness\",\n" + 
				"          \"name\": \"Gregariousness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.765372217791855,\n" + 
				"          \"significant\": true\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"big5_agreeableness\",\n" + 
				"      \"name\": \"Agreeableness\",\n" + 
				"      \"category\": \"personality\",\n" + 
				"      \"percentile\": 0.8580034481075867,\n" + 
				"      \"significant\": true,\n" + 
				"      \"children\": [\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_altruism\",\n" + 
				"          \"name\": \"Altruism\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.3647630892068823,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_cooperation\",\n" + 
				"          \"name\": \"Cooperation\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.4446601259999595,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_modesty\",\n" + 
				"          \"name\": \"Modesty\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.41380634043568587,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_morality\",\n" + 
				"          \"name\": \"Uncompromising\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.4704690931519337,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_sympathy\",\n" + 
				"          \"name\": \"Sympathy\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.3895370798038277,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_trust\",\n" + 
				"          \"name\": \"Trust\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.6442270337822056,\n" + 
				"          \"significant\": true\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"big5_neuroticism\",\n" + 
				"      \"name\": \"Emotional range\",\n" + 
				"      \"category\": \"personality\",\n" + 
				"      \"percentile\": 0.6918024797191394,\n" + 
				"      \"significant\": true,\n" + 
				"      \"children\": [\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_anger\",\n" + 
				"          \"name\": \"Fiery\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.40368496466866327,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_anxiety\",\n" + 
				"          \"name\": \"Prone to worry\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.33132834291295116,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_depression\",\n" + 
				"          \"name\": \"Melancholy\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.19722793550998585,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_immoderation\",\n" + 
				"          \"name\": \"Immoderation\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.34686729097194513,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_self_consciousness\",\n" + 
				"          \"name\": \"Self-consciousness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.388608268481245,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_vulnerability\",\n" + 
				"          \"name\": \"Susceptible to stress\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.2982247942178574,\n" + 
				"          \"significant\": true\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"needs\": [\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_challenge\",\n" + 
				"      \"name\": \"Challenge\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.9827953401602001,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_closeness\",\n" + 
				"      \"name\": \"Closeness\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.72264321320099,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_curiosity\",\n" + 
				"      \"name\": \"Curiosity\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.7425116633091317,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_excitement\",\n" + 
				"      \"name\": \"Excitement\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.8684057043557365,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_harmony\",\n" + 
				"      \"name\": \"Harmony\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.8143365951087438,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_ideal\",\n" + 
				"      \"name\": \"Ideal\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.933159502875314,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_liberty\",\n" + 
				"      \"name\": \"Liberty\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.847293368969775,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_love\",\n" + 
				"      \"name\": \"Love\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.7834845877475028,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_practicality\",\n" + 
				"      \"name\": \"Practicality\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.8116892883252307,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_self_expression\",\n" + 
				"      \"name\": \"Self-expression\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.7267389138586307,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_stability\",\n" + 
				"      \"name\": \"Stability\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.9749889630209259,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_structure\",\n" + 
				"      \"name\": \"Structure\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.9293164686930341,\n" + 
				"      \"significant\": true\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"values\": [\n" + 
				"    {\n" + 
				"      \"trait_id\": \"value_conservation\",\n" + 
				"      \"name\": \"Conservation\",\n" + 
				"      \"category\": \"values\",\n" + 
				"      \"percentile\": 0.9516083704167001,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"value_openness_to_change\",\n" + 
				"      \"name\": \"Openness to change\",\n" + 
				"      \"category\": \"values\",\n" + 
				"      \"percentile\": 0.807672580636824,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"value_hedonism\",\n" + 
				"      \"name\": \"Hedonism\",\n" + 
				"      \"category\": \"values\",\n" + 
				"      \"percentile\": 0.8529759970344186,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"value_self_enhancement\",\n" + 
				"      \"name\": \"Self-enhancement\",\n" + 
				"      \"category\": \"values\",\n" + 
				"      \"percentile\": 0.9424726774444241,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"value_self_transcendence\",\n" + 
				"      \"name\": \"Self-transcendence\",\n" + 
				"      \"category\": \"values\",\n" + 
				"      \"percentile\": 0.6350518411502489,\n" + 
				"      \"significant\": true\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"behavior\": [\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_sunday\",\n" + 
				"      \"name\": \"Sunday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.1028971028971029\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_monday\",\n" + 
				"      \"name\": \"Monday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.0919080919080919\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_tuesday\",\n" + 
				"      \"name\": \"Tuesday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.19080919080919082\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_wednesday\",\n" + 
				"      \"name\": \"Wednesday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.13186813186813187\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_thursday\",\n" + 
				"      \"name\": \"Thursday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.19080919080919082\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_friday\",\n" + 
				"      \"name\": \"Friday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.12887112887112886\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_saturday\",\n" + 
				"      \"name\": \"Saturday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.16283716283716285\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0000\",\n" + 
				"      \"name\": \"0:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.11188811188811189\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0100\",\n" + 
				"      \"name\": \"1:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.08191808191808192\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0200\",\n" + 
				"      \"name\": \"2:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.0979020979020979\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0300\",\n" + 
				"      \"name\": \"3:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.03296703296703297\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0400\",\n" + 
				"      \"name\": \"4:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.012987012987012988\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0500\",\n" + 
				"      \"name\": \"5:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.005994005994005994\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0600\",\n" + 
				"      \"name\": \"6:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.030969030969030968\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0700\",\n" + 
				"      \"name\": \"7:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0800\",\n" + 
				"      \"name\": \"8:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0900\",\n" + 
				"      \"name\": \"9:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1000\",\n" + 
				"      \"name\": \"10:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.013986013986013986\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1100\",\n" + 
				"      \"name\": \"11:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.006993006993006993\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1200\",\n" + 
				"      \"name\": \"12:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.005994005994005994\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1300\",\n" + 
				"      \"name\": \"1:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.03596403596403597\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1400\",\n" + 
				"      \"name\": \"2:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.006993006993006993\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1500\",\n" + 
				"      \"name\": \"3:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.026973026973026972\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1600\",\n" + 
				"      \"name\": \"4:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.025974025974025976\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1700\",\n" + 
				"      \"name\": \"5:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.10589410589410589\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1800\",\n" + 
				"      \"name\": \"6:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.055944055944055944\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1900\",\n" + 
				"      \"name\": \"7:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.03296703296703297\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_2000\",\n" + 
				"      \"name\": \"8:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.054945054945054944\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_2100\",\n" + 
				"      \"name\": \"9:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.1048951048951049\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_2200\",\n" + 
				"      \"name\": \"10:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.08491508491508491\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_2300\",\n" + 
				"      \"name\": \"11:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.06293706293706294\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"consumption_preferences\": [\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_shopping\",\n" + 
				"      \"name\": \"Purchasing Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_automobile_ownership_cost\",\n" + 
				"          \"name\": \"Likely to be sensitive to ownership cost when buying automobiles\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_automobile_safety\",\n" + 
				"          \"name\": \"Likely to prefer safety when buying automobiles\",\n" + 
				"          \"score\": 0.5\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_clothes_quality\",\n" + 
				"          \"name\": \"Likely to prefer quality when buying clothes\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_clothes_style\",\n" + 
				"          \"name\": \"Likely to prefer style when buying clothes\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_clothes_comfort\",\n" + 
				"          \"name\": \"Likely to prefer comfort when buying clothes\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_influence_brand_name\",\n" + 
				"          \"name\": \"Likely to be influenced by brand name when making product purchases\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_influence_utility\",\n" + 
				"          \"name\": \"Likely to be influenced by product utility when making product purchases\",\n" + 
				"          \"score\": 0.5\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_influence_online_ads\",\n" + 
				"          \"name\": \"Likely to be influenced by online ads when making product purchases\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_influence_social_media\",\n" + 
				"          \"name\": \"Likely to be influenced by social media when making product purchases\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_influence_family_members\",\n" + 
				"          \"name\": \"Likely to be influenced by family when making product purchases\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_spur_of_moment\",\n" + 
				"          \"name\": \"Likely to indulge in spur of the moment purchases\",\n" + 
				"          \"score\": 0.5\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_credit_card_payment\",\n" + 
				"          \"name\": \"Likely to prefer using credit cards for shopping\",\n" + 
				"          \"score\": 0\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_health_and_activity\",\n" + 
				"      \"name\": \"Health & Activity Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_eat_out\",\n" + 
				"          \"name\": \"Likely to eat out frequently\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_gym_membership\",\n" + 
				"          \"name\": \"Likely to have a gym membership\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_outdoor\",\n" + 
				"          \"name\": \"Likely to like outdoor activities\",\n" + 
				"          \"score\": 1\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_environmental_concern\",\n" + 
				"      \"name\": \"Environmental Concern Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_concerned_environment\",\n" + 
				"          \"name\": \"Likely to be concerned about the environment\",\n" + 
				"          \"score\": 0\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_entrepreneurship\",\n" + 
				"      \"name\": \"Entrepreneurship Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_start_business\",\n" + 
				"          \"name\": \"Likely to consider starting a business in next few years\",\n" + 
				"          \"score\": 1\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_movie\",\n" + 
				"      \"name\": \"Movie Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_romance\",\n" + 
				"          \"name\": \"Likely to like romance movies\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_adventure\",\n" + 
				"          \"name\": \"Likely to like adventure movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_horror\",\n" + 
				"          \"name\": \"Likely to like horror movies\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_musical\",\n" + 
				"          \"name\": \"Likely to like musical movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_historical\",\n" + 
				"          \"name\": \"Likely to like historical movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_science_fiction\",\n" + 
				"          \"name\": \"Likely to like science-fiction movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_war\",\n" + 
				"          \"name\": \"Likely to like war movies\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_drama\",\n" + 
				"          \"name\": \"Likely to like drama movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_action\",\n" + 
				"          \"name\": \"Likely to like action movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_documentary\",\n" + 
				"          \"name\": \"Likely to like documentary movies\",\n" + 
				"          \"score\": 0\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_music\",\n" + 
				"      \"name\": \"Music Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_rap\",\n" + 
				"          \"name\": \"Likely to like rap music\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_country\",\n" + 
				"          \"name\": \"Likely to like country music\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_r_b\",\n" + 
				"          \"name\": \"Likely to like R&B music\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_hip_hop\",\n" + 
				"          \"name\": \"Likely to like hip hop music\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_live_event\",\n" + 
				"          \"name\": \"Likely to attend live musical events\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_playing\",\n" + 
				"          \"name\": \"Likely to have experience playing music\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_latin\",\n" + 
				"          \"name\": \"Likely to like Latin music\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_rock\",\n" + 
				"          \"name\": \"Likely to like rock music\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_classical\",\n" + 
				"          \"name\": \"Likely to like classical music\",\n" + 
				"          \"score\": 0\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_reading\",\n" + 
				"      \"name\": \"Reading Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_read_frequency\",\n" + 
				"          \"name\": \"Likely to read often\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_books_entertainment_magazines\",\n" + 
				"          \"name\": \"Likely to read entertainment magazines\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_books_non_fiction\",\n" + 
				"          \"name\": \"Likely to read non-fiction books\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_books_financial_investing\",\n" + 
				"          \"name\": \"Likely to read financial investment books\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_books_autobiographies\",\n" + 
				"          \"name\": \"Likely to read autobiographical books\",\n" + 
				"          \"score\": 0\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_volunteering\",\n" + 
				"      \"name\": \"Volunteering Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_volunteer\",\n" + 
				"          \"name\": \"Likely to volunteer for social causes\",\n" + 
				"          \"score\": 0\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"warnings\": []\n" + 
				"}";
		
		String str2 = "{\n" + 
				"  \"word_count\": 15128,\n" + 
				"  \"processed_language\": \"en\",\n" + 
				"  \"personality\": [\n" + 
				"    {\n" + 
				"      \"trait_id\": \"big5_openness\",\n" + 
				"      \"name\": \"Openness\",\n" + 
				"      \"category\": \"personality\",\n" + 
				"      \"percentile\": 0.8048087217136444,\n" + 
				"      \"significant\": true,\n" + 
				"      \"children\": [\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_adventurousness\",\n" + 
				"          \"name\": \"Adventurousness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.9045974768772609,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_artistic_interests\",\n" + 
				"          \"name\": \"Artistic interests\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.9790201511572232,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_emotionality\",\n" + 
				"          \"name\": \"Emotionality\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.994913153896493,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_imagination\",\n" + 
				"          \"name\": \"Imagination\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.8714517724206957,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_intellect\",\n" + 
				"          \"name\": \"Intellect\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.8835958016905736,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_liberalism\",\n" + 
				"          \"name\": \"Authority-challenging\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.6486344859769552,\n" + 
				"          \"significant\": true\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"big5_conscientiousness\",\n" + 
				"      \"name\": \"Conscientiousness\",\n" + 
				"      \"category\": \"personality\",\n" + 
				"      \"percentile\": 0.8102947333861581,\n" + 
				"      \"significant\": true,\n" + 
				"      \"children\": [\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_achievement_striving\",\n" + 
				"          \"name\": \"Achievement striving\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.8447942535266852,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_cautiousness\",\n" + 
				"          \"name\": \"Cautiousness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.7225672485998348,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_dutifulness\",\n" + 
				"          \"name\": \"Dutifulness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.8414459590561425,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_orderliness\",\n" + 
				"          \"name\": \"Orderliness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.6154468578992103,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_self_discipline\",\n" + 
				"          \"name\": \"Self-discipline\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.8344273426362091,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_self_efficacy\",\n" + 
				"          \"name\": \"Self-efficacy\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.7041262378443771,\n" + 
				"          \"significant\": true\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"big5_extraversion\",\n" + 
				"      \"name\": \"Extraversion\",\n" + 
				"      \"category\": \"personality\",\n" + 
				"      \"percentile\": 0.6425580321109656,\n" + 
				"      \"significant\": true,\n" + 
				"      \"children\": [\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_activity_level\",\n" + 
				"          \"name\": \"Activity level\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.8860397181738027,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_assertiveness\",\n" + 
				"          \"name\": \"Assertiveness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.6742837190539857,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_cheerfulness\",\n" + 
				"          \"name\": \"Cheerfulness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.9430030813836863,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_excitement_seeking\",\n" + 
				"          \"name\": \"Excitement-seeking\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.5936685312560733,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_friendliness\",\n" + 
				"          \"name\": \"Outgoing\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.9603396711358603,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_gregariousness\",\n" + 
				"          \"name\": \"Gregariousness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.6570127643040263,\n" + 
				"          \"significant\": true\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"big5_agreeableness\",\n" + 
				"      \"name\": \"Agreeableness\",\n" + 
				"      \"category\": \"personality\",\n" + 
				"      \"percentile\": 0.9441476521819426,\n" + 
				"      \"significant\": true,\n" + 
				"      \"children\": [\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_altruism\",\n" + 
				"          \"name\": \"Altruism\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.9925983032671803,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_cooperation\",\n" + 
				"          \"name\": \"Cooperation\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.8640865926209997,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_modesty\",\n" + 
				"          \"name\": \"Modesty\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.7777409427743319,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_morality\",\n" + 
				"          \"name\": \"Uncompromising\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.8952857419791442,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_sympathy\",\n" + 
				"          \"name\": \"Sympathy\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.994659354665798,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_trust\",\n" + 
				"          \"name\": \"Trust\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.9031062247867112,\n" + 
				"          \"significant\": true\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"big5_neuroticism\",\n" + 
				"      \"name\": \"Emotional range\",\n" + 
				"      \"category\": \"personality\",\n" + 
				"      \"percentile\": 0.5011424258038871,\n" + 
				"      \"significant\": true,\n" + 
				"      \"children\": [\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_anger\",\n" + 
				"          \"name\": \"Fiery\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.16919226490209138,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_anxiety\",\n" + 
				"          \"name\": \"Prone to worry\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.42130232455149075,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_depression\",\n" + 
				"          \"name\": \"Melancholy\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.1490766395109473,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_immoderation\",\n" + 
				"          \"name\": \"Immoderation\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.2702704377158157,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_self_consciousness\",\n" + 
				"          \"name\": \"Self-consciousness\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.29325681738170095,\n" + 
				"          \"significant\": true\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"trait_id\": \"facet_vulnerability\",\n" + 
				"          \"name\": \"Susceptible to stress\",\n" + 
				"          \"category\": \"personality\",\n" + 
				"          \"percentile\": 0.3862483573834635,\n" + 
				"          \"significant\": true\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"needs\": [\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_challenge\",\n" + 
				"      \"name\": \"Challenge\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.6699981453953766,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_closeness\",\n" + 
				"      \"name\": \"Closeness\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.8366389466400257,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_curiosity\",\n" + 
				"      \"name\": \"Curiosity\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.9338147737801363,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_excitement\",\n" + 
				"      \"name\": \"Excitement\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.7368905165835753,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_harmony\",\n" + 
				"      \"name\": \"Harmony\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.9681096581919244,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_ideal\",\n" + 
				"      \"name\": \"Ideal\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.6846651401448991,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_liberty\",\n" + 
				"      \"name\": \"Liberty\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.7944143551559293,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_love\",\n" + 
				"      \"name\": \"Love\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.8187640742747349,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_practicality\",\n" + 
				"      \"name\": \"Practicality\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.34469863540722323,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_self_expression\",\n" + 
				"      \"name\": \"Self-expression\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.8698181973941164,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_stability\",\n" + 
				"      \"name\": \"Stability\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.8705205013979334,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"need_structure\",\n" + 
				"      \"name\": \"Structure\",\n" + 
				"      \"category\": \"needs\",\n" + 
				"      \"percentile\": 0.7464328575415977,\n" + 
				"      \"significant\": true\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"values\": [\n" + 
				"    {\n" + 
				"      \"trait_id\": \"value_conservation\",\n" + 
				"      \"name\": \"Conservation\",\n" + 
				"      \"category\": \"values\",\n" + 
				"      \"percentile\": 0.886672268738759,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"value_openness_to_change\",\n" + 
				"      \"name\": \"Openness to change\",\n" + 
				"      \"category\": \"values\",\n" + 
				"      \"percentile\": 0.8696769334020679,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"value_hedonism\",\n" + 
				"      \"name\": \"Hedonism\",\n" + 
				"      \"category\": \"values\",\n" + 
				"      \"percentile\": 0.4401896345423549,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"value_self_enhancement\",\n" + 
				"      \"name\": \"Self-enhancement\",\n" + 
				"      \"category\": \"values\",\n" + 
				"      \"percentile\": 0.6488575994223418,\n" + 
				"      \"significant\": true\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"value_self_transcendence\",\n" + 
				"      \"name\": \"Self-transcendence\",\n" + 
				"      \"category\": \"values\",\n" + 
				"      \"percentile\": 0.8280778884301451,\n" + 
				"      \"significant\": true\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"behavior\": [\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_sunday\",\n" + 
				"      \"name\": \"Sunday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.2217782217782218\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_monday\",\n" + 
				"      \"name\": \"Monday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.42157842157842157\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_tuesday\",\n" + 
				"      \"name\": \"Tuesday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.07092907092907093\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_wednesday\",\n" + 
				"      \"name\": \"Wednesday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.01098901098901099\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_thursday\",\n" + 
				"      \"name\": \"Thursday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.12087912087912088\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_friday\",\n" + 
				"      \"name\": \"Friday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.07692307692307693\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_saturday\",\n" + 
				"      \"name\": \"Saturday\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.07692307692307693\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0000\",\n" + 
				"      \"name\": \"0:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.4515484515484515\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0100\",\n" + 
				"      \"name\": \"1:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.12087912087912088\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0200\",\n" + 
				"      \"name\": \"2:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.02097902097902098\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0300\",\n" + 
				"      \"name\": \"3:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.0939060939060939\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0400\",\n" + 
				"      \"name\": \"4:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.01998001998001998\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0500\",\n" + 
				"      \"name\": \"5:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0600\",\n" + 
				"      \"name\": \"6:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0700\",\n" + 
				"      \"name\": \"7:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.01098901098901099\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0800\",\n" + 
				"      \"name\": \"8:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_0900\",\n" + 
				"      \"name\": \"9:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1000\",\n" + 
				"      \"name\": \"10:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1100\",\n" + 
				"      \"name\": \"11:00 am\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1200\",\n" + 
				"      \"name\": \"12:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1300\",\n" + 
				"      \"name\": \"1:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1400\",\n" + 
				"      \"name\": \"2:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1500\",\n" + 
				"      \"name\": \"3:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.02197802197802198\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1600\",\n" + 
				"      \"name\": \"4:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.02197802197802198\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1700\",\n" + 
				"      \"name\": \"5:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.03196803196803197\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1800\",\n" + 
				"      \"name\": \"6:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.00999000999000999\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_1900\",\n" + 
				"      \"name\": \"7:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.01098901098901099\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_2000\",\n" + 
				"      \"name\": \"8:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.02197802197802198\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_2100\",\n" + 
				"      \"name\": \"9:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_2200\",\n" + 
				"      \"name\": \"10:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.04095904095904096\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"trait_id\": \"behavior_2300\",\n" + 
				"      \"name\": \"11:00 pm\",\n" + 
				"      \"category\": \"behavior\",\n" + 
				"      \"percentage\": 0.12187812187812187\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"consumption_preferences\": [\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_shopping\",\n" + 
				"      \"name\": \"Purchasing Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_automobile_ownership_cost\",\n" + 
				"          \"name\": \"Likely to be sensitive to ownership cost when buying automobiles\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_automobile_safety\",\n" + 
				"          \"name\": \"Likely to prefer safety when buying automobiles\",\n" + 
				"          \"score\": 0.5\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_clothes_quality\",\n" + 
				"          \"name\": \"Likely to prefer quality when buying clothes\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_clothes_style\",\n" + 
				"          \"name\": \"Likely to prefer style when buying clothes\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_clothes_comfort\",\n" + 
				"          \"name\": \"Likely to prefer comfort when buying clothes\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_influence_brand_name\",\n" + 
				"          \"name\": \"Likely to be influenced by brand name when making product purchases\",\n" + 
				"          \"score\": 0.5\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_influence_utility\",\n" + 
				"          \"name\": \"Likely to be influenced by product utility when making product purchases\",\n" + 
				"          \"score\": 0.5\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_influence_online_ads\",\n" + 
				"          \"name\": \"Likely to be influenced by online ads when making product purchases\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_influence_social_media\",\n" + 
				"          \"name\": \"Likely to be influenced by social media when making product purchases\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_influence_family_members\",\n" + 
				"          \"name\": \"Likely to be influenced by family when making product purchases\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_spur_of_moment\",\n" + 
				"          \"name\": \"Likely to indulge in spur of the moment purchases\",\n" + 
				"          \"score\": 0.5\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_credit_card_payment\",\n" + 
				"          \"name\": \"Likely to prefer using credit cards for shopping\",\n" + 
				"          \"score\": 0\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_health_and_activity\",\n" + 
				"      \"name\": \"Health & Activity Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_eat_out\",\n" + 
				"          \"name\": \"Likely to eat out frequently\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_gym_membership\",\n" + 
				"          \"name\": \"Likely to have a gym membership\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_outdoor\",\n" + 
				"          \"name\": \"Likely to like outdoor activities\",\n" + 
				"          \"score\": 0\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_environmental_concern\",\n" + 
				"      \"name\": \"Environmental Concern Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_concerned_environment\",\n" + 
				"          \"name\": \"Likely to be concerned about the environment\",\n" + 
				"          \"score\": 0\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_entrepreneurship\",\n" + 
				"      \"name\": \"Entrepreneurship Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_start_business\",\n" + 
				"          \"name\": \"Likely to consider starting a business in next few years\",\n" + 
				"          \"score\": 1\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_movie\",\n" + 
				"      \"name\": \"Movie Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_romance\",\n" + 
				"          \"name\": \"Likely to like romance movies\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_adventure\",\n" + 
				"          \"name\": \"Likely to like adventure movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_horror\",\n" + 
				"          \"name\": \"Likely to like horror movies\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_musical\",\n" + 
				"          \"name\": \"Likely to like musical movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_historical\",\n" + 
				"          \"name\": \"Likely to like historical movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_science_fiction\",\n" + 
				"          \"name\": \"Likely to like science-fiction movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_war\",\n" + 
				"          \"name\": \"Likely to like war movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_drama\",\n" + 
				"          \"name\": \"Likely to like drama movies\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_action\",\n" + 
				"          \"name\": \"Likely to like action movies\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_movie_documentary\",\n" + 
				"          \"name\": \"Likely to like documentary movies\",\n" + 
				"          \"score\": 0\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_music\",\n" + 
				"      \"name\": \"Music Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_rap\",\n" + 
				"          \"name\": \"Likely to like rap music\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_country\",\n" + 
				"          \"name\": \"Likely to like country music\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_r_b\",\n" + 
				"          \"name\": \"Likely to like R&B music\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_hip_hop\",\n" + 
				"          \"name\": \"Likely to like hip hop music\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_live_event\",\n" + 
				"          \"name\": \"Likely to attend live musical events\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_playing\",\n" + 
				"          \"name\": \"Likely to have experience playing music\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_latin\",\n" + 
				"          \"name\": \"Likely to like Latin music\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_rock\",\n" + 
				"          \"name\": \"Likely to like rock music\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_music_classical\",\n" + 
				"          \"name\": \"Likely to like classical music\",\n" + 
				"          \"score\": 0\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_reading\",\n" + 
				"      \"name\": \"Reading Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_read_frequency\",\n" + 
				"          \"name\": \"Likely to read often\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_books_entertainment_magazines\",\n" + 
				"          \"name\": \"Likely to read entertainment magazines\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_books_non_fiction\",\n" + 
				"          \"name\": \"Likely to read non-fiction books\",\n" + 
				"          \"score\": 0\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_books_financial_investing\",\n" + 
				"          \"name\": \"Likely to read financial investment books\",\n" + 
				"          \"score\": 1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_books_autobiographies\",\n" + 
				"          \"name\": \"Likely to read autobiographical books\",\n" + 
				"          \"score\": 0\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"consumption_preference_category_id\": \"consumption_preferences_volunteering\",\n" + 
				"      \"name\": \"Volunteering Preferences\",\n" + 
				"      \"consumption_preferences\": [\n" + 
				"        {\n" + 
				"          \"consumption_preference_id\": \"consumption_preferences_volunteer\",\n" + 
				"          \"name\": \"Likely to volunteer for social causes\",\n" + 
				"          \"score\": 0\n" + 
				"        }\n" + 
				"      ]\n" + 
				"    }\n" + 
				"  ],\n" + 
				"  \"warnings\": []\n" + 
				"}\n" + 
				"";
//		System.out.println(analysisResult.getJsonObject());
		Ideal ideal = new Ideal(0.5, 0.5, 0.5, 0.5, 0.5);
		
		Player player = new Player();
		player.setAttributes(PlayerConfig.getBasicStatus(1));
		analysisResult.generateFactor(ideal, player);
		analysisResult.setJsonObject(str);
		analysisResult.generateFactor(ideal, player);
		analysisResult.setJsonObject(str2);
		analysisResult.generateFactor(ideal, player);
	}
}
