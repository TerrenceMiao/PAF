package au.com.auspost.microservice.services;

import au.com.auspost.microservice.domain.DeliveryPoint;
import au.com.auspost.microservice.domain.DeliveryPointGroup;
import au.com.auspost.microservice.domain.Locality;
import au.com.auspost.microservice.dto.PostalAddress;
import au.com.auspost.microservice.repositories.DeliveryPointGroupRepository;
import au.com.auspost.microservice.repositories.DeliveryPointRepository;
import au.com.auspost.microservice.repositories.LocalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by terrence on 31/10/15.
 */
public interface PostalAddressService {

    String getDpidFromPostalAddress(PostalAddress postalAddress);

    void setLocalityRepository(LocalityRepository localityRepository);
    void setDeliveryPointGroupRepository(DeliveryPointGroupRepository deliveryPointGroupRepository);
    void setDeliveryPointRepository(DeliveryPointRepository deliveryPointRepository);

    @Service
    class Impl implements PostalAddressService {

        @Autowired
        private LocalityRepository localityRepository;
        @Autowired
        private DeliveryPointGroupRepository deliveryPointGroupRepository;
        @Autowired
        private DeliveryPointRepository deliveryPointRepository;

        @Override
        public String getDpidFromPostalAddress(PostalAddress postalAddress) {

            List<Locality> localityList = localityRepository.findByLocalityName(postalAddress.getLocalityName());

            List<DeliveryPointGroup> deliveryPointGroupList = deliveryPointGroupRepository.findByLocalityIdAndStreetNameAndStreetType(
                    localityList.get(0).getLocalityId(), postalAddress.getStreetName(), postalAddress.getStreetType());

            List<DeliveryPoint> deliveryPointList = deliveryPointRepository.findByDelivyPointGroupIdAndHouseNbr1(
                    deliveryPointGroupList.get(0).getDelivyPointGroupId(), postalAddress.getHouseNumber1());

            return deliveryPointList.get(0).getDelivyPointId();
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
