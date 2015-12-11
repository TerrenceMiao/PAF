package au.com.auspost.microservice.services;

import au.com.auspost.microservice.Constants;
import au.com.auspost.microservice.domain.DeliveryPoint;
import au.com.auspost.microservice.domain.DeliveryPointGroup;
import au.com.auspost.microservice.domain.Locality;
import au.com.auspost.microservice.dto.PostalAddress;
import au.com.auspost.microservice.repositories.DeliveryPointGroupRepository;
import au.com.auspost.microservice.repositories.DeliveryPointRepository;
import au.com.auspost.microservice.repositories.LocalityRepository;
import au.com.auspost.microservice.utils.StringFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by terrence on 31/10/15.
 */
public interface PostalAddressService {

    String getDpidFromPostalAddress(PostalAddress postalAddress);
    List<Locality> getAllOrderedLocalities();
    List<String> getAllStreetType();

    void setLocalityRepository(LocalityRepository localityRepository);
    void setDeliveryPointGroupRepository(DeliveryPointGroupRepository deliveryPointGroupRepository);
    void setDeliveryPointRepository(DeliveryPointRepository deliveryPointRepository);

    @Service
    class Impl implements PostalAddressService {

        private static final Logger LOG = LoggerFactory.getLogger(PostalAddressService.class);

        @Autowired
        private LocalityRepository localityRepository;
        @Autowired
        private DeliveryPointGroupRepository deliveryPointGroupRepository;
        @Autowired
        private DeliveryPointRepository deliveryPointRepository;

        @Override
        @Cacheable(value = "dpidCache")
        public String getDpidFromPostalAddress(PostalAddress postalAddress) {

            LOG.info("Query for " + postalAddress);

            List<Locality> localityList = localityRepository.findByLocalityNameAndStateAndPostcode(
                    postalAddress.getLocalityName(), postalAddress.getState(), postalAddress.getPostcode());

            if (localityList.size() == 0) {
                LOG.warn("Locality list is empty for locality [{}]", postalAddress.getLocalityName());

                return Constants.EMPTY_STRING;
            }

            List<DeliveryPointGroup> deliveryPointGroupList = deliveryPointGroupRepository.findByLocalityIdAndStreetNameAndStreetType(
                    localityList.get(0).getLocalityId(), postalAddress.getStreetName(), postalAddress.getStreetType());

            if (deliveryPointGroupList.size() == 0) {
                LOG.warn("Delivery Point Group list is empty for locality id [{}], street name [{}], street type [{}]",
                        localityList.get(0).getLocalityId(), postalAddress.getStreetName(), postalAddress.getStreetType());

                return Constants.EMPTY_STRING;
            }

            List<DeliveryPoint> deliveryPointList = deliveryPointRepository.findByDelivyPointGroupIdAndHouseNbr1(
                    deliveryPointGroupList.get(0).getDelivyPointGroupId(),
                    StringFormatter.padLeftWithZero(postalAddress.getHouseNumber1(), Constants.STREETS_NUMBER_LENGTH));

            if (deliveryPointList.size() == 0) {
                LOG.warn("Delivery Point list is empty for delivery point group id [{}], house number [{}]",
                        deliveryPointGroupList.get(0).getDelivyPointGroupId(),
                        StringFormatter.padLeftWithZero(postalAddress.getHouseNumber1(), Constants.STREETS_NUMBER_LENGTH));

                return Constants.EMPTY_STRING;
            } else {
                return deliveryPointList.get(0).getDelivyPointId();
            }
        }

        @Override
        public List<Locality> getAllOrderedLocalities() {

            return localityRepository.findAllOrderedLocalities();
        }

        @Override
        public List<String> getAllStreetType() {

            return deliveryPointGroupRepository.findAllStreetType();
        }

        @Override
        public void setLocalityRepository(LocalityRepository localityRepository) {

            this.localityRepository = localityRepository;
        }

        @Override
        public void setDeliveryPointGroupRepository(DeliveryPointGroupRepository deliveryPointGroupRepository) {

            this.deliveryPointGroupRepository = deliveryPointGroupRepository;
        }

        @Override
        public void setDeliveryPointRepository(DeliveryPointRepository deliveryPointRepository) {

            this.deliveryPointRepository = deliveryPointRepository;
        }
    }

}
